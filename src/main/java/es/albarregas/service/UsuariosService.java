package es.albarregas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.albarregas.model.Perfil;
import es.albarregas.model.Publicacion;
import es.albarregas.model.Rol;
import es.albarregas.model.Usuario;
import es.albarregas.repository.PerfilesRepo;
import es.albarregas.repository.PublicacionesRepo;
import es.albarregas.repository.UsuariosRepo;
/**
 * Servicio para operaciones de usuarios
 * @author Fran Luna
 *
 */
@Service
public class UsuariosService {

	@Autowired
	private UsuariosRepo repo;
	
	@Autowired
	private PerfilesRepo perfilesRepo;
	
	@Autowired
	private PublicacionesRepo publicacionesRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	/**
	 * Guarda o actualiza un usuario
	 * @param usuario
	 */
	public void save(Usuario u) {
		u.setPasswd(encoder.encode(u.getPasswd()));
		repo.save(u);
	}
	/**
	 * Devuelve la lista con todos los usuarios
	 * @return usuarios
	 */
	public List<Usuario> get() {
		return repo.findAll();
	}
	/**
	 * Devuelve un usuario dado su nombre de usuario
	 * @param username
	 * @return usuario
	 */
	public Usuario getByUsername (String username) {
		return repo.findByUsernameLike(username);
	}
	/**
	 * Comprueba si un email está en uso por algún usuario
	 * @param email
	 * @return valido
	 */
	public boolean checkMail (String email) {
		boolean valido = true;
		Usuario u = repo.findByEmailLike(email);
		if (u != null) {
			valido = false;
		}
		return valido;
	}
	/**
	 * Comprueba si un nombre de usuario ya está en uso
	 * @param username
	 * @return valido
	 */
	public boolean checkUsername (String username) {
		boolean valido = true;
		Usuario u = repo.findByUsernameLike(username);
		if (u != null) {
			valido = false;
		}
		return valido;
	}
	/**
	 * Devuelve una instancia de un perfil dado un rol
	 * @param rol
	 * @return
	 */
	public Perfil getPerfilByRol (Rol rol) {
		return perfilesRepo.findByRol(rol);
	}
	/**
	 * Guarda o actualiza un perfil
	 * @param perfil
	 */
	public void guardarPerfil (Perfil perfil) {
		perfilesRepo.save(perfil);
	}
	/**
	 * Borra un usuario dado su nombre de usuario
	 * @param username
	 */
	@Transactional
	public void borrarPorUsername(String username) {
		Usuario u = repo.findByUsernameLike(username);
		List<Publicacion> publicaciones = u.getPublicaciones();
		for (Publicacion p : publicaciones) {
			p.setUsuario(null);
			publicacionesRepo.save(p);
		}
		u.setPublicaciones(null);
		repo.delete(u);
	}
	
}
