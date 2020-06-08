package es.albarregas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.albarregas.model.Etiqueta;
import es.albarregas.repository.EtiquetasRepo;
/**
 * Servicio para operaciones de etiquetas
 * @author Fran Luna
 *
 */
@Service
public class EtiquetasService {
	@Autowired
	private EtiquetasRepo repo;
	/**
	 * Devuelve una lista con todas las etiquetas
	 * @return etiquetas
	 */
	public List<Etiqueta> findAll () {
		return repo.findAll();
	}
	/**
	 * Devuelve una etiqueta dado su id
	 * @param id
	 * @return etiqueta
	 */
	public Etiqueta findById(int id) {
		Optional<Etiqueta> optional = repo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	/**
	 * Guarda o actualiza una etiqueta
	 * @param etiqueta
	 */
	public void save (Etiqueta e) {
		repo.save(e);
	}
	/**
	 * Borra una etiqueta dado su id
	 * @param id
	 */
	public void deleteById(int id) {
		repo.deleteById(id);
	}
}
