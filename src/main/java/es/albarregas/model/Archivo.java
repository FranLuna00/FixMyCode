package es.albarregas.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import es.albarregas.utils.Utiles;
/**
 * Modelo de archivo
 * @author Fran Luna
 *
 */
@Entity
@Table(name="archivos")
public class Archivo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String archivo;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipoArchivo", columnDefinition = "ENUM('MARCA','JAVASCRIPT','CSS','SASS','JAVA','SQL','PROPERTIES')")
    private TipoArchivo tipoArchivo;
	@Transient
	private String texto;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTexto(String ruta) {
		try {
			texto = (new Utiles()).leerArchivo(archivo, ruta);
			return texto;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void setTexto(String texto) {
		this.texto = texto;
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
}
