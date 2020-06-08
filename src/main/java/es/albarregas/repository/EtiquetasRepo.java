package es.albarregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.model.Etiqueta;
/**
 * Repositorio para etiquetas
 * @author Fran Luna
 *
 */
public interface EtiquetasRepo extends JpaRepository<Etiqueta, Integer> {
	/**
	 * Borra una etiqueta dado su id
	 * @param id
	 */
	void deleteById(int id);
}
