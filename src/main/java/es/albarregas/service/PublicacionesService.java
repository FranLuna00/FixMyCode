package es.albarregas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.albarregas.model.Archivo;
import es.albarregas.model.Etiqueta;
import es.albarregas.model.Publicacion;
import es.albarregas.model.Respuesta;
import es.albarregas.model.Valoracion;
import es.albarregas.repository.ArchivosRepo;
import es.albarregas.repository.PublicacionesRepo;
import es.albarregas.repository.RespuestasRepo;
import es.albarregas.repository.ValoracionesRepo;

/**
 * Servicio para operaciones con publicaciones
 * 
 * @author Fran Luna
 *
 */
@Service
public class PublicacionesService {
	
	@Autowired
	private PublicacionesRepo repo;

	@Autowired
	private ArchivosRepo archivosRepo;

	@Autowired
	private RespuestasRepo respuestasRepo;

	@Autowired
	private ValoracionesRepo valoracionesRepo;
	/**
	 * Devuelve una lista con las 5 últimas publicaciones 
	 * @return
	 */
	public List<Publicacion> get() {
		return repo.findFirst5ByOrderByFechaPublicacionDesc();
	}
	/**
	 * Devuelve una página con el número de páginas pedido 
	 * @param pageable
	 * @return publicaciones
	 */
	public Page<Publicacion> get(Pageable pageable) {
		return repo.findAll(pageable);
	}
	/**
	 * Devuelve todas las publicaciones ordenadas por número de valoraciones
	 * @return publicaciones
	 */
	public List<Publicacion> getByValoraciones() {
		return repo.findByValoraciones(PageRequest.of(0, 10));
	}
	/**
	 * Devuelve una publicación dado su id
	 * @param id
	 * @return publicacion
	 */
	public Publicacion getById(int id) {
		Optional<Publicacion> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}
	/**
	 * Devuelve la última publicación persistida en la base de datos
	 * @return
	 */
	public Publicacion getLast() {
		return repo.findTopByOrderByIdDesc();
	}
	/**
	 * Guarda o actualiza una publicación
	 * @param publicacion
	 */
	public void save(Publicacion p) {
		if (p.getArchivos() != null) {
			for (Archivo a : p.getArchivos()) {
				archivosRepo.save(a);
			}
		}
		repo.save(p);
	}
	/**
	 * Guarda una respuesta
	 * @param respuesta
	 * @return respuesta
	 */
	public Respuesta saveRespuesta(Respuesta r) {
		respuestasRepo.save(r);
		respuestasRepo.flush();
		return r;
	}
	/**
	 * Guarda una valoración
	 * @param valoracion
	 */
	public void valorar(Valoracion v) {
		valoracionesRepo.save(v);
		valoracionesRepo.flush();
	}
	/**
	 * Devuelve las publicaciones que contengan las etiquetas parametrizadas
	 * @param etiquetas
	 * @param pageable
	 * @return publicaciones
	 */
	public Page<Publicacion> findByEtiquetas(List<Etiqueta> etiquetas, Pageable pageable) {
		Page<Publicacion> publicaciones = repo.findByEtiquetasIn(etiquetas, pageable);
		return publicaciones;
	}
	/**
	 * Devuelve la lista de publicaciones cuyo título contenga t
	 * @param t
	 * @return publicaciones
	 */
	public List<Publicacion> findByTituloLike(String titulo) {
		return repo.findByTituloContaining(titulo);
	}
	/**
	 * Devuelve las publicaciones que contengan las etiquetas e y cuyo título contenga t
	 * @param e
	 * @param t
	 * @return publicaciones
	 */
	public List<Publicacion> findByEtiquetasTitulo(List<Etiqueta> etiquetas, String busqueda) {
		return repo.findByTituloContainingAndEtiquetasIn(busqueda, etiquetas);
	}

}
