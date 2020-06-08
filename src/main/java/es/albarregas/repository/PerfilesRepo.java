package es.albarregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.model.Perfil;
import es.albarregas.model.Rol;
/**
 * Repositorio para perfiles
 * @author Fran Luna
 *
 */
public interface PerfilesRepo extends JpaRepository<Perfil, Integer> {
	/**
	 * Devuelve un perfil dado su rol
	 * @param rol
	 * @return perfil
	 */
	public Perfil findByRol (Rol rol);
}
