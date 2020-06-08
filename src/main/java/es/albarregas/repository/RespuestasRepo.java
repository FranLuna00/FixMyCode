package es.albarregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.model.Respuesta;
/**
 * Repositorio para respuestas
 * @author Fran Luna
 *
 */
public interface RespuestasRepo extends JpaRepository<Respuesta, Integer> {

}
