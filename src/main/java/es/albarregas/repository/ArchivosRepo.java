package es.albarregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.model.Archivo;
/**
 * Repositorio para archivos
 * @author Fran Luna
 *
 */
public interface ArchivosRepo extends JpaRepository<Archivo, Integer> {

}
