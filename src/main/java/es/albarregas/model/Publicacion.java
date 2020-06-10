package es.albarregas.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * Modelo de publicación
 * @author Fran Luna
 *
 */
@Entity
@Table(name = "publicaciones")
public class Publicacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Usuario usuario;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPublicacion = new Date();
	@OneToMany(fetch = FetchType.EAGER)
	private List<Valoracion> valoraciones;
	@OneToMany(cascade = javax.persistence.CascadeType.ALL)
	private List<Archivo> archivos;
	@ManyToMany(cascade = javax.persistence.CascadeType.REFRESH)
	private List<Etiqueta> etiquetas;
	@Column(length = 60, nullable = false)
	private String titulo;
	@Column(length = 600, nullable = false)
	private String detalles;
	@OneToMany(cascade = javax.persistence.CascadeType.ALL)
	private List<Respuesta> respuestas;

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public void addRespuesta(Respuesta respuesta) {
		if (respuestas == null) {
			respuestas = new ArrayList<>();
		}
		respuestas.add(respuesta);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public void addValoracion(Valoracion valoracion) {
			Valoracion valoracionBorrar = null;
		if (valoraciones == null) {
			valoraciones = new ArrayList<>();
		} else {
			Iterator<Valoracion> it = valoraciones.iterator();
			while (it.hasNext()) {
				Valoracion v = it.next();
				if (valoracion.getUsuario().getId() == v.getUsuario().getId()) {
					valoracionBorrar = v;
				}
			}
		}
		if (valoracionBorrar != null) {
			valoraciones.remove(valoracionBorrar);
		}
		valoraciones.add(valoracion);
	}
	
	public List<Archivo> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<Archivo> archivo) {
		this.archivos = archivo;
	}

	public void addArchivo(Archivo archivo) {
		if (archivos == null) {
			archivos = new ArrayList<Archivo>();
		}
		archivos.add(archivo);
	}

	public List<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}
	
	public int getValoracionesPositivas() {
		int valoracionesP = 0;
		Iterator<Valoracion> it = valoraciones.iterator();
		while (it.hasNext()) {
			Valoracion v = it.next();
			if (v.getValoracion().equals("POSITIVA")) {
				valoracionesP++;
			}
		}
		return valoracionesP;
	}

	public int getValoracionesNegativas() {
		int valoracionesP = 0;
		Iterator<Valoracion> it = valoraciones.iterator();
		while (it.hasNext()) {
			Valoracion v = it.next();
			if (v.getValoracion().equals("NEGATIVA")) {
				valoracionesP++;
			}
		}
		return valoracionesP;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publicacion other = (Publicacion) obj;
		if (id != other.id)
			return false;
		return true;
	}

}