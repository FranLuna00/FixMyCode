package es.albarregas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
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

import es.albarregas.model.Archivo;
import es.albarregas.model.Etiqueta;
import es.albarregas.model.Publicacion;
import es.albarregas.model.Respuesta;
import es.albarregas.model.TipoArchivo;
import es.albarregas.model.Usuario;
import es.albarregas.service.EtiquetasService;
import es.albarregas.service.PublicacionesService;
import es.albarregas.service.UsuariosService;
import es.albarregas.utils.Utiles;
/**
 * Controlador para peticiones de publicaciones
 * @author Fran Luna
 *
 */
@Controller
@RequestMapping("/publicaciones")
public class PublicacionesController {

	@Autowired
	private UsuariosService usuariosServ;

	@Autowired
	private PublicacionesService publiServ;

	@Autowired
	private EtiquetasService etiquetasServ;

	@Value("${fixmycode.ruta.archivos}")
	private String ruta;
	/**
	 * Redirección a formulario de publicación
	 * @param publicacion
	 * @param model
	 * @return vista formulario publicación
	 */
	@GetMapping("/nueva")
	public String publicacionForm(Publicacion publicacion, Model model) {
		model.addAttribute("etiquetas", etiquetasServ.findAll());
		return "/publicaciones/formPublicacion";
	}
	/**
	 * Crea una publicación
	 * @param publicacion
	 * @param result
	 * @param multiPart
	 * @param etiquetas
	 * @param request
	 * @return vista detalle publicación creada
	 */
	@PostMapping("/nueva")
	public String nuevaPublicacion(Publicacion publicacion, BindingResult result,
			@RequestParam("archivoSubir") MultipartFile[] multiPart, @RequestParam("etiquetas") String[] etiquetas,
			HttpServletRequest request) {
		Usuario u = usuariosServ.getByUsername(request.getUserPrincipal().getName());
		publicacion.setUsuario(u);
		List<Archivo> archivos = new ArrayList<>();
		for (MultipartFile archivo : multiPart) {
			Archivo a = new Archivo();
			TipoArchivo tipoArchivoE;
			switch (FilenameUtils.getExtension(archivo.getOriginalFilename())) {
			case "html":
				tipoArchivoE = TipoArchivo.MARCA;
				break;
			case "js":
				tipoArchivoE = TipoArchivo.JAVASCRIPT;
				break;
			case "css":
				tipoArchivoE = TipoArchivo.CSS;
				break;
			case "scss":
				tipoArchivoE = TipoArchivo.SASS;
				break;
			case "java":
				tipoArchivoE = TipoArchivo.JAVA;
				break;
			case "sql":
				tipoArchivoE = TipoArchivo.SQL;
				break;
			case "properties":
				tipoArchivoE = TipoArchivo.PROPERTIES;
				break;
			default:
				tipoArchivoE = TipoArchivo.JAVA;
				break;
			}
			a.setTipoArchivo(tipoArchivoE);
			a.setArchivo(Utiles.guardarArchivo(archivo, ruta));
			archivos.add(a);
		}
		publicacion.setArchivos(archivos);
		List<Etiqueta> etiquetasObj = new ArrayList<>();
		for (String id : etiquetas) {
			Etiqueta etiqueta = etiquetasServ.findById(Integer.parseInt(id));
			etiquetasObj.add(etiqueta);
		}
		publicacion.setEtiquetas(etiquetasObj);
		publiServ.save(publicacion);
		u.addPublicacion(publicacion);
		usuariosServ.save(u);
		int id = publiServ.getLast().getId();
		return "redirect:/publicaciones/" + id;
	}
	/**
	 * Lee una publicación por id en la url y muestra sus detalles
	 * @param model
	 * @param id
	 * @return vista detalle publicación
	 */
	@GetMapping("/{id}")
	public String detallePublicacion(Model model, @PathVariable int id) {
		Publicacion p = publiServ.getById(id);
		if (p != null) {
			model.addAttribute("publicacion", p);
			return "/publicaciones/detalle";
		} else {
			return "/publicaciones/error";
		}
	}
	/**
	 * Responder a una publicación con id id
	 * @param id
	 * @param request
	 * @param respuesta
	 * @return
	 */
	@GetMapping("/respuesta/{id}")
	public String respuesta(@PathVariable int id, HttpServletRequest request,
			@RequestParam("respuesta") String respuesta) {
		Usuario u = usuariosServ.getByUsername(request.getUserPrincipal().getName());
		Publicacion p = publiServ.getById(id);
		Respuesta r = new Respuesta();
		r.setPublicacion(p);
		r.setUsuario(u);
		r.setRespuesta(respuesta);
		Respuesta r1 = publiServ.saveRespuesta(r);
		p.addRespuesta(r1);
		publiServ.save(p);
		return "redirect:/publicaciones/" + id;
	}

	@GetMapping("/explorar")
	public String explorar() {
		return "";
	}

}
