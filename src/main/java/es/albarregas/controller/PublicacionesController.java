package es.albarregas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
 * 
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

	/**
	 * Redirección a formulario de publicación
	 * 
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
	 * 
	 * @param publicacion
	 * @param result
	 * @param multiPart
	 * @param etiquetas
	 * @param request
	 * @return vista detalle publicación creada
	 */
	@PostMapping("/nueva")
	public String nuevaPublicacion(Publicacion publicacion, BindingResult result,
			@RequestParam("archivoSubir") MultipartFile[] multiPart, @RequestParam("etiquetas") int[] etiquetas,
			HttpServletRequest request, Model model) {
		boolean error = false;
		if (result.hasErrors()) {
			error = true;
		}
		if (publicacion.getTitulo().equals("")) {
			result.addError(new ObjectError("Título vacío", "El título no puede estar vacío"));
			error = true;
		}
		Usuario u = usuariosServ.getByUsername(request.getUserPrincipal().getName());
		publicacion.setUsuario(u);
		String ruta = request.getServletContext().getRealPath("/WEB-INF/classes/static/archivos/");
		for (MultipartFile archivo : multiPart) {
			if (!archivo.isEmpty()) {
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
				publicacion.addArchivo(a);
			}
		}

		if (etiquetas[0] != 0) {
			List<Etiqueta> etiquetasObj = new ArrayList<>();
			for (int id : etiquetas) {
				Etiqueta etiqueta = etiquetasServ.findById(id);
				etiquetasObj.add(etiqueta);
			}
			publicacion.setEtiquetas(etiquetasObj);
		} else {
			publicacion.setEtiquetas(null);
		}
		if (error) {
			model.addAttribute("etiquetas", etiquetasServ.findAll());
			return "/publicaciones/formPublicacion";
		} else {
			publiServ.save(publicacion);
			u.addPublicacion(publicacion);
			usuariosServ.save(u);
			int id = publiServ.getLast().getId();
			return "redirect:/publicaciones/" + id;
		}
	}

	/**
	 * Lee una publicación por id en la url y muestra sus detalles
	 * 
	 * @param model
	 * @param id
	 * @return vista detalle publicación
	 */
	@GetMapping("/{id}")
	public String detallePublicacion(Model model, @PathVariable int id, HttpServletRequest request) {
		Publicacion p = publiServ.getById(id);
		if (p != null) {
			List<Texto> textos = new ArrayList<>();
			String ruta = request.getServletContext().getRealPath("/WEB-INF/classes/static/archivos/");
			for (Archivo a : p.getArchivos()) {
				Texto t = new Texto();
				t.setTexto(a.getTexto(ruta));
				t.setTipo(a.getTipoArchivo());
				textos.add(t);
			}
			model.addAttribute("publicacion", p);
			model.addAttribute("textos", textos);
			return "/publicaciones/detalle";
		} else {
			return "/publicaciones/error";
		}
	}

	/**
	 * Responder a una publicación con id id
	 * 
	 * @param id
	 * @param request
	 * @param respuesta
	 * @return redirección a publicación respondida
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

	/**
	 * Redirige a la vista de búsqueda
	 * 
	 * @param model
	 * @return vista explorar
	 */
	@GetMapping("/explorar")
	public String explorar(Model model) {
		Page<Publicacion> publicaciones = publiServ.get(PageRequest.of(0, 15, Sort.by("fechaPublicacion")));
		model.addAttribute("etiquetas", etiquetasServ.findAll());
		model.addAttribute("publicaciones", publicaciones);
		return "/publicaciones/explorar";
	}

	/**
	 * Realiza una búsqueda dados etiquetas y título a buscar
	 * 
	 * @param model
	 * @param etiquetas
	 * @param busqueda
	 * @return vista explorar
	 */
	@PostMapping(value = "/explorar", params = "enviar")
	public String buscar(Model model, @RequestParam("etiquetas") String[] etiquetas,
			@RequestParam("busqueda") String busqueda) {
		model.addAttribute("etiquetas", etiquetasServ.findAll());
		List<Publicacion> publicaciones;
		List<Etiqueta> etiquetasLista = new ArrayList<>();

		if (busqueda.equals("") && etiquetas[0].equals("nulo")) {
			publicaciones = publiServ.get(PageRequest.of(0, 20, Sort.by("fechaPublicacion"))).toList();
		} else {
			if (!etiquetas[0].equals("nulo")) {
				for (String id : etiquetas) {
					Etiqueta etiqueta = etiquetasServ.findById(Integer.parseInt(id));
					etiquetasLista.add(etiqueta);
				}
				if (!busqueda.equals("")) {
					publicaciones = publiServ.findByEtiquetasTitulo(etiquetasLista, busqueda);
				} else {
					publicaciones = publiServ
							.findByEtiquetas(etiquetasLista, PageRequest.of(0, 20, Sort.by("fechaPublicacion")))
							.toList();
				}
			} else {
				publicaciones = publiServ.findByTituloLike(busqueda);
			}
		}
		model.addAttribute("publicaciones", publicaciones.stream().distinct().collect(Collectors.toList()));
		model.addAttribute("etiquetasBuscadas", etiquetasLista);
		return "/publicaciones/explorar";
	}
}

class Texto {
	private String texto;
	private TipoArchivo tipo;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public TipoArchivo getTipo() {
		return tipo;
	}

	public void setTipo(TipoArchivo tipo) {
		this.tipo = tipo;
	}

}
