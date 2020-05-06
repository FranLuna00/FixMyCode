package es.albarregas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.albarregas.model.Publicacion;
import es.albarregas.repository.PublicacionesRepo;

@Service
public class PublicacionesService {
	
	@Autowired
	private PublicacionesRepo repo;
	
	public List<Publicacion> get() {
		return repo.findAll();
	}

}
