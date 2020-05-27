package es.albarregas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.albarregas.model.Etiqueta;
import es.albarregas.repository.EtiquetasRepo;

@Service
public class EtiquetasService {
	@Autowired
	private EtiquetasRepo repo;

	public List<Etiqueta> findAll () {
		return repo.findAll();
	}
	
	public Etiqueta findById(int id) {
		Optional<Etiqueta> optional = repo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public void save (Etiqueta e) {
		repo.save(e);
	}
	
	public void deleteById(int id) {
		repo.deleteById(id);
	}
}
