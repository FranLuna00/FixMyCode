package es.albarregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.model.Valoracion;
/**
 * Repositorio para valoraciones
 * @author Fran Luna
 *
 */
public interface ValoracionesRepo extends JpaRepository<Valoracion, Integer> {

}
