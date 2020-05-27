package es.albarregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.model.Etiqueta;

public interface EtiquetasRepo extends JpaRepository<Etiqueta, Integer> {
	void deleteById(int id);
}
