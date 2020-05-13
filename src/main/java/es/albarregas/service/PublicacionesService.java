package es.albarregas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.albarregas.model.Publicacion;
import es.albarregas.repository.PublicacionesRepo;

@Service
public class PublicacionesService {
	
	@Autowired
	private PublicacionesRepo repo;
	
	public List<Publicacion> get() {
		return repo.findFirst15ByOrderByFechaPublicacion();
	}
	
	public Publicacion getById(int id) {
		Optional<Publicacion> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

	public void save (Publicacion p) {
		repo.save(p);
	}
	
}
