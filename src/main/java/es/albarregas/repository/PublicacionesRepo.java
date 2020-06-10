package es.albarregas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.albarregas.model.Etiqueta;
import es.albarregas.model.Publicacion;
/**
 * Repositorio para publicaciones
 * @author Fran Luna
 *
 */
public interface PublicacionesRepo extends JpaRepository<Publicacion, Integer> {
	
	public List<Publicacion> findFirst5ByOrderByFechaPublicacionDesc();
	public Publicacion findTopByOrderByIdDesc();
	public List<Publicacion> findByTituloContaining(String titulo);
	public List<Publicacion> findByTituloContainingAndEtiquetasIn(String titulo, List<Etiqueta> etiquetas);
	public Page<Publicacion> findByEtiquetasIn(List<Etiqueta> etiquetas, Pageable pageable);
	@Query("select p from Publicacion p left join p.valoraciones v group by p order by count(v) desc")
	public List<Publicacion> findByValoraciones(Pageable pageable);
}
