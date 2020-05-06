package es.albarregas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.albarregas.service.PublicacionesService;

@Controller
public class HomeController {
	
	@Autowired
	private PublicacionesService publiServ;
	
	@GetMapping("/")
	public String mostrarHome (Model model) {
		model.addAttribute("publicaciones", publiServ.get());
		return "index";
	}


	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
}
