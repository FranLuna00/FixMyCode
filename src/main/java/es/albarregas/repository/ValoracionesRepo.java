package es.albarregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.model.Valoracion;

public interface ValoracionesRepo extends JpaRepository<Valoracion, Integer> {

}
