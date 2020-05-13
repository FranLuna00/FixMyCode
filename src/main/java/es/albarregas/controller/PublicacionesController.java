package es.albarregas.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.albarregas.model.Publicacion;
import es.albarregas.model.Usuario;
import es.albarregas.service.PublicacionesService;
import es.albarregas.service.UsuariosService;
import es.albarregas.utils.Utiles;

@Controller
@RequestMapping("/publicaciones")
public class PublicacionesController {
	
	@Autowired
	private UsuariosService usuariosServ;
	
	@Autowired
	private PublicacionesService publiServ;
	
	@Value("${fixmycode.ruta.archivos}")
	private String ruta;
	
	@GetMapping("/nueva")
	public String publicacionForm (Publicacion publicacion) {
		return "/publicaciones/formPublicacion";
	}
	
	@PostMapping("/nueva")
	public String nuevaPublicacion (Publicacion publicacion, BindingResult result,
			@RequestParam("archivoSubir") MultipartFile multiPart,
			HttpServletRequest request) {
		Usuario u = usuariosServ.getByUsername(request.getUserPrincipal().getName());
		publicacion.setUsuario(u);
		publicacion.setArchivo(Utiles.guardarArchivo(multiPart, ruta));
		publiServ.save(publicacion);
		
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String detallePublicacion (Model model, @PathVariable int id) {
		Publicacion p = publiServ.getById(id);
		if (p != null) {
			
			model.addAttribute("publicacion", p);
			
			try {
				
				String archivo = Utiles.leerArchivo(ruta+p.getArchivo());
				model.addAttribute("archivo", archivo);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "/publicaciones/detalle";
			
		} else {
			return "/publicaciones/error";
		}
	}
	
}
