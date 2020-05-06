package es.albarregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.model.Usuario;

public interface UsuariosRepo extends JpaRepository<Usuario, Integer> {
	public Usuario findByEmailLike (String email);
	public Usuario findByUsernameLike (String email);
}
