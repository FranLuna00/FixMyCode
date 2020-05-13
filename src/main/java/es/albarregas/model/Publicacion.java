package es.albarregas.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="publicaciones")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Usuario usuario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion = new Date();
    @OneToMany(fetch = FetchType.EAGER)
    private List<Valoracion> valoraciones;
    private boolean aprobada = false;
    private String archivo;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipoArchivo", columnDefinition = "ENUM('MARCA','JAVASCRIPT','CSS','SASS','JAVA','SQL','PROPERTIES')")
    private TipoArchivo tipoArchivo;
    @ElementCollection
    @CollectionTable(name = "etiquetas")
    private List<String> etiquetas;
    private String titulo;
    private String detalles;

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
		valoraciones.add(valoracion);
	}

	public boolean isAprobada() {
        return aprobada;
    }

    public void setAprobada(boolean aprobada) {
        this.aprobada = aprobada;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public TipoArchivo getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(TipoArchivo tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public List<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<String> etiquetas) {
        this.etiquetas = etiquetas;
    }

	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}
	
	public int getValoracionesPositivas () {
		int valoracionesP = 0;
		Iterator<Valoracion> it = valoraciones.iterator();
		while (it.hasNext()) {
			Valoracion v = it.next();
			if (v.getValoracion() == "POSITIVA") {
				valoracionesP++;
			}
		}
		return valoracionesP;
	}
	
	public int getValoracionesNegativas () {
		int valoracionesP = 0;
		Iterator<Valoracion> it = valoraciones.iterator();
		while (it.hasNext()) {
			Valoracion v = it.next();
			if (v.getValoracion() != "POSITIVA") {
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

    

}