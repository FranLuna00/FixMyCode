package es.albarregas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.albarregas.model.Archivo;
import es.albarregas.model.Etiqueta;
import es.albarregas.model.Publicacion;
import es.albarregas.model.Respuesta;
import es.albarregas.model.Valoracion;
import es.albarregas.repository.ArchivosRepo;
import es.albarregas.repository.EtiquetasRepo;
import es.albarregas.repository.PublicacionesRepo;
import es.albarregas.repository.RespuestasRepo;
import es.albarregas.repository.ValoracionesRepo;

@Service
public class PublicacionesService {
	
	@Autowired
	private PublicacionesRepo repo;
	
	@Autowired
	private ArchivosRepo archivosRepo;
	
	@Autowired
	private RespuestasRepo respuestasRepo;
	
	@Autowired
	private ValoracionesRepo valoracionesRepo;
	
	@Autowired
	private EtiquetasRepo etiquetasRepo;

	public List<Publicacion> get() {
		return repo.findFirst15ByOrderByFechaPublicacionDesc();
	}
	
	public Publicacion getById(int id) {
		Optional<Publicacion> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}
	
	public Publicacion getLast() {
		return repo.findTopByOrderByIdDesc();
	}

	public void save (Publicacion p) {
		for (Archivo a : p.getArchivos()) {
			archivosRepo.save(a);
		}
		repo.save(p);
	}
	
	public Respuesta saveRespuesta (Respuesta r) {
		respuestasRepo.save(r);
		respuestasRepo.flush();
		return r;
	}
	
	public void valorar (Valoracion v) {
		valoracionesRepo.save(v);
		valoracionesRepo.flush();
	}
	
	
}
