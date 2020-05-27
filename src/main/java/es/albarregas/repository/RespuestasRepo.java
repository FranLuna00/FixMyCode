package es.albarregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.model.Respuesta;

public interface RespuestasRepo extends JpaRepository<Respuesta, Integer> {

}
