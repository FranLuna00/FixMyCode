package es.albarregas.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.albarregas.model.Rol;
import es.albarregas.model.Usuario;
import es.albarregas.service.UsuariosService;
import es.albarregas.utils.Utiles;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Value("${fixmycode.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private UsuariosService usuariosServ;

	@GetMapping("/registro")
	public String registroForm(Usuario usuario) {
		return "/usuarios/registro";
	}

	@GetMapping("/admin")
	public String administrarUsuarios(Model model) {
		model.addAttribute("usuarios", usuariosServ.get());
		return "/usuarios/admin";
	}
	
	@GetMapping("/admin/borrar/{username}")
	public String borrarUsuario(@PathVariable("username") String username) {
		usuariosServ.borrarPorUsername(username);
		return "redirect:/usuarios/admin";
	}

	@GetMapping("/admin/disable/{username}")
	public String deshabilitarUsuario(@PathVariable("username") String username) {
		Usuario u = usuariosServ.getByUsername(username);
		if (u.getEnabled() == 0) {
			u.setEnabled(1);
		} else {
			u.setEnabled(0);
		}
		usuariosServ.save(u);
		return "redirect:/usuarios/admin";
	}
	
	@GetMapping("/{username}")
	public String perfilUsuario (@PathVariable("username") String username, Model model) {
		model.addAttribute("user", usuariosServ.getByUsername(username));
		return "usuarios/perfil";
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, false));
	}

	@PostMapping("/guardar")
	public String registro(Usuario usuario, BindingResult result,
			@RequestParam("archivoImagen") MultipartFile multiPart,
			@RequestParam("passwdConfirm") String passwdConfirm) {
		boolean error = false;
		usuario.setPerfil(usuariosServ.getPerfilByRol(Rol.REGISTRADO));
		if (result.hasErrors()) {
			error = true;
		}
		if (!usuario.getPasswd().equals(passwdConfirm)) {
			result.addError(new ObjectError("Contraseña errónea",
					"La confirmación de la contraseña debe coincidir con la inicial"));
			error = true;
		}
		if (usuario.getUsername().equals("") || !usuariosServ.checkUsername(usuario.getUsername())) {
			result.addError(
					new ObjectError("Nombre de usuario", "El nombre de usuario no puede estar vacío o repetido"));
			error = true;
		}
		if (usuario.getEmail().equals("") || !usuariosServ.checkMail(usuario.getEmail())) {
			result.addError(new ObjectError("Email", "El email no puede estar vacío o repetido"));
			error = true;
		}
		if (usuario.getPasswd().length() < 6) {
			result.addError(new ObjectError("Contraseña errónea", "La contraseña debe tener al menos 7 caracteres"));
			error = true;
		}
		if (!multiPart.isEmpty()) {
			String nombreImg = Utiles.guardarArchivo(multiPart, ruta);
			if (nombreImg != null) {
				usuario.setAvatar(nombreImg);
			} else {
				usuario.setAvatar("avatar.png");
			}
		}
		if (error) {
			return "/usuarios/registro";
		} else {
			usuariosServ.save(usuario);
			return "redirect:/";
		}
	}

}
