package es.albarregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.model.Publicacion;

public interface PublicacionesRepo extends JpaRepository<Publicacion, Integer> {

}
