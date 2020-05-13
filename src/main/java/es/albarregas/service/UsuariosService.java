package es.albarregas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.albarregas.model.Perfil;
import es.albarregas.model.Rol;
import es.albarregas.model.Usuario;
import es.albarregas.repository.PerfilesRepo;
import es.albarregas.repository.UsuariosRepo;

@Service
public class UsuariosService {

	@Autowired
	private UsuariosRepo repo;
	
	@Autowired
	private PerfilesRepo perfilesRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public void save(Usuario u) {
		u.setPasswd(encoder.encode(u.getPasswd()));
		repo.save(u);
	}
	
	public List<Usuario> get() {
		return repo.findAll();
	}
	
	public Usuario getByUsername (String username) {
		return repo.findByUsernameLike(username);
	}
	
	public boolean checkMail (String email) {
		boolean valido = true;
		Usuario u = repo.findByEmailLike(email);
		if (u != null) {
			valido = false;
		}
		return valido;
	}

	public boolean checkUsername (String username) {
		boolean valido = true;
		Usuario u = repo.findByUsernameLike(username);
		if (u != null) {
			valido = false;
		}
		return valido;
	}
	
	public Perfil getPerfilByRol (Rol rol) {
		return perfilesRepo.findByRol(rol);
	}
	
	public void guardarPerfil (Perfil perfil) {
		perfilesRepo.save(perfil);
	}
	
	@Transactional
	public void borrarPorUsername(String username) {
		repo.deleteByUsername(username);
	}
	
}
