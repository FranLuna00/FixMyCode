package es.albarregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.model.Perfil;
import es.albarregas.model.Rol;

public interface PerfilesRepo extends JpaRepository<Perfil, Integer> {
	public Perfil findByRol (Rol rol);
}
