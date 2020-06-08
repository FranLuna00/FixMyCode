package es.albarregas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.albarregas.model.Etiqueta;
import es.albarregas.model.Usuario;
import es.albarregas.service.EtiquetasService;
import es.albarregas.service.UsuariosService;

/**
 * Controlador para peticiones de usuario registrado como administrador
 * @author Fran Luna
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private EtiquetasService etiquetasServ;

	@Autowired
	private UsuariosService usuariosServ;
	
	/**
	 * Redirige a la gestión de etiquetas
	 * @param model
	 * @return vista gestión de etiquetas
	 */
	@GetMapping("/etiquetas")
	public String gestionEtiquetas(Model model) {
		model.addAttribute("etiquetas", etiquetasServ.findAll());
		return "/admin/etiquetas";
	}
	/**
	 * Borra una etiqueta
	 * @param id
	 * @return vista gestión de etiquetas
	 */
	@GetMapping("/etiquetas/borrar/{id}")
	public String borrarEtiqueta(@PathVariable int id) {
		etiquetasServ.deleteById(id);
		return "redirect:/admin/etiquetas";
	}
	/**
	 * Añade una etiqueta
	 * @param nombre
	 * @return vista gestión de etiquetas
	 */
	@PostMapping("/etiquetas/nueva")
	public String nuevaEtiqueta(@RequestParam(name = "nombre") String nombre) {
		Etiqueta etiqueta = new Etiqueta();
		etiqueta.setNombre(nombre);
		etiquetasServ.save(etiqueta);
		return "redirect:/admin/etiquetas";
	}
	/**
	 * Redirige a la gestión de usuarios
	 * @param model
	 * @return vista gestión de usuarios
	 */
	@GetMapping("/usuarios")
	public String administrarUsuarios(Model model) {
		model.addAttribute("usuarios", usuariosServ.get());
		return "/admin/usuarios";
	}
	/**
	 * Borra un usuario y desvincula sus publicaciones del mismo
	 * @param username
	 * @return vista gestión de usuarios
	 */
	@GetMapping("/usuarios/borrar/{username}")
	public String borrarUsuario(@PathVariable("username") String username) {
		usuariosServ.borrarPorUsername(username);
		return "redirect:/admin/usuarios";
	}
	/**
	 * Deshabilita un usuario
	 * @param username
	 * @return vista gestión de usuarios
	 */
	@GetMapping("/usuarios/disable/{username}")
	public String deshabilitarUsuario(@PathVariable("username") String username) {
		Usuario u = usuariosServ.getByUsername(username);
		if (u.getEnabled() == 0) {
			u.setEnabled(1);
		} else {
			u.setEnabled(0);
		}
		usuariosServ.save(u);
		return "redirect:/admin/usuarios";
	}
	

}
