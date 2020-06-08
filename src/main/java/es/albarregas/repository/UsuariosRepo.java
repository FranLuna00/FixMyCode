package es.albarregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.model.Usuario;
/**
 * Repositorio para usuarios
 * @author Fran Luna
 *
 */
public interface UsuariosRepo extends JpaRepository<Usuario, Integer> {
	/**
	 * Devuelve un usuario dado su email
	 * @param email
	 * @return usuario
	 */
	public Usuario findByEmailLike (String email);
	/**
	 * Devuelve un usuario dado su nombre de usuario
	 * @param email
	 * @return usuario
	 */
	public Usuario findByUsernameLike (String email);
	/**
	 * Borra un usuario dado su nombre de usuario
	 * @param username
	 */
	public void deleteByUsername(String username);
}
