package es.albarregas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.model.Publicacion;

public interface PublicacionesRepo extends JpaRepository<Publicacion, Integer> {
	public List<Publicacion> findFirst15ByOrderByFechaPublicacion();
}
