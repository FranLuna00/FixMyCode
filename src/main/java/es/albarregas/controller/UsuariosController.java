package es.albarregas.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

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

/**
 * Gestiona peticiones de usuarios
 * 
 * @author Fran Luna
 *
 */
@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Value("${fixmycode.ruta.imagenes}")
	private String ruta;

	@Autowired
	private UsuariosService usuariosServ;

	/**
	 * Redirección a formulario de registro
	 * 
	 * @param usuario
	 * @return vista login
	 */
	@GetMapping("/registro")
	public String registroForm(Usuario usuario) {
		return "/usuarios/registro";
	}

	/**
	 * Redirige a perfil de usuario con nombre de usuario especificado en url
	 * 
	 * @param username
	 * @param model
	 * @return
	 */
	@GetMapping("/{username}")
	public String perfilUsuario(@PathVariable("username") String username, Model model) {
		model.addAttribute("user", usuariosServ.getByUsername(username));
		return "usuarios/perfil";
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, false));
	}

	/**
	 * Guarda un usuario
	 * 
	 * @param usuario
	 * @param result
	 * @param multiPart
	 * @param passwdConfirm
	 * @return vista index
	 */
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
			}
		} else {
			usuario.setAvatar("avatar.png");
		}
		if (error) {
			return "/usuarios/registro";
		} else {
			usuariosServ.save(usuario);
			return "redirect:/login";
		}
	}

	/**
	 * Redirección a formulario de modificación de perfil
	 * 
	 * @param u
	 * @param model
	 * @param request
	 * @return vista editar usuario
	 */
	@GetMapping("/editar")
	public String editar(Usuario u, Model model, HttpServletRequest request) {
		model.addAttribute("usuario", usuariosServ.getByUsername(request.getUserPrincipal().getName()));
		return "/usuarios/editarPerfil";
	}

	/**
	 * Edita un usuario logeado
	 * 
	 * @param usuario
	 * @param result
	 * @param model
	 * @param request
	 * @param multiPart
	 * @param passwdConfirm
	 * @param newPasswd
	 * @return vista detalle usuario logeado
	 */
	@PostMapping("/editar")
	public String editarUsuario(Usuario usuario, BindingResult result, Model model, HttpServletRequest request,
			@RequestParam("archivoImagen") MultipartFile multiPart, @RequestParam("passwdConfirm") String passwdConfirm,
			@RequestParam("new-passwd") String newPasswd) {
		boolean error = false;
		Usuario oldUsuario = usuariosServ.getByUsername(request.getUserPrincipal().getName());
		if (result.hasErrors()) {
			error = true;
		}
		if (!newPasswd.equals("")) {
			if (!newPasswd.equals(passwdConfirm)) {
				result.addError(new ObjectError("Contraseña errónea",
						"La confirmación de la contraseña debe coincidir con la inicial"));
				error = true;
			} else {
				usuario.setPasswd(newPasswd);
			}
			if (usuario.getPasswd().length() < 6) {
				result.addError(
						new ObjectError("Contraseña errónea", "La contraseña debe tener al menos 7 caracteres"));
				error = true;
			}
		} else {
			usuario.setPasswd(oldUsuario.getPasswd());
		}
		if (usuario.getUsername().equals("")) {
			result.addError(new ObjectError("Nombre de usuario", "El nombre de usuario no puede estar vacío"));
			error = true;
		} else {
			if (!usuario.getUsername().equals(request.getUserPrincipal().getName())
					&& !usuariosServ.checkUsername(usuario.getUsername())) {
				result.addError(new ObjectError("Nombre de usuario", "El nombre de usuario no puede estar repetido"));
				error = true;
			}
		}
		if (!multiPart.isEmpty()) {
			String nombreImg = Utiles.guardarArchivo(multiPart, ruta);
			if (nombreImg != null) {
				usuario.setAvatar(nombreImg);
			}
		} else {
			usuario.setAvatar(oldUsuario.getAvatar());
		}
		if (error) {
			return "redirect:/usuarios/editar";
		} else {
			usuario.setId(oldUsuario.getId());
			usuario.setPerfil(oldUsuario.getPerfil());
			usuario.setEmail(oldUsuario.getEmail());
			usuariosServ.save(usuario);
			return "redirect:/usuarios/" + usuario.getUsername();
		}
	}

}
