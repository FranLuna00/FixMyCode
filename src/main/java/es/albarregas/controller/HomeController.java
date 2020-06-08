package es.albarregas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.albarregas.model.Publicacion;
import es.albarregas.service.PublicacionesService;
/**
 * Gestiona peticiones de página principal y logout
 * @author Fran Luna
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	private PublicacionesService publiServ;
	/**
	 * Redirige a página principal
	 * @param model
	 * @return vista index
	 */
	@GetMapping("/")
	public String mostrarHome (Model model) {
		List<Publicacion> publicaciones = publiServ.getByValoraciones();
		model.addAttribute("publicaciones", publicaciones);
		return "index";
	}

	
}
