package es.albarregas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import es.albarregas.model.Publicacion;
import es.albarregas.model.Valoracion;
import es.albarregas.service.PublicacionesService;
import es.albarregas.service.UsuariosService;
/**
 * Gestiona peticiones asíncronas
 * @author Fran Luna
 *
 */
@Controller
public class AjaxController {
	
	@Autowired
	private PublicacionesService publiServ;

	@Autowired
	private UsuariosService usuariosServ;
	/**
	 * Valora una publicación
	 * @param datos
	 * @param request
	 * 
	 */
	@PostMapping("/publicaciones/valorar")
	public @ResponseBody String valoracion(@RequestBody Datos datos, HttpServletRequest request) {
		Valoracion v = new Valoracion();
		if (datos.getValoracion().equals("positiva")) {
			v.setValoracion("POSITIVA");
		} else {
			v.setValoracion("NEGATIVA");
		}
		v.setUsuario(
				usuariosServ.getByUsername(request.getUserPrincipal().getName()));
		Publicacion p = publiServ.getById(Integer.parseInt(datos.getIdPublicacion()));
		p.addValoracion(v);
		publiServ.valorar(v);
		publiServ.save(p);
		
		return "";
	}
	
}
/**
 * Bean para recoger los datos de la petición valorar
 * @author Fran Luna
 *
 */
class Datos {
	private String valoracion;
	private String idPublicacion;
	public String getValoracion() {
		return valoracion;
	}
	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}
	public String getIdPublicacion() {
		return idPublicacion;
	}
	public void setIdPublicacion(String idPublicacion) {
		this.idPublicacion = idPublicacion;
	}
	
}
