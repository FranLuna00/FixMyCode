package es.albarregas.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
/**
 * Clase con utilidades
 * @author Fran Luna
 *
 */
public class Utiles {
	/**
	 * Guarda un archivo en el sistema
	 * @param multiPart
	 * @param ruta
	 * @return nombre del archivo
	 */
	public static String guardarArchivo(MultipartFile multiPart, String ruta) {
		// Obtenemos el nombre original del archivo.
		String nombreOriginal = multiPart.getOriginalFilename();
		nombreOriginal.replace(" ", "-");
		String nombreFinal = "" + randomAlphaNumeric(8) + nombreOriginal;
		try {
			// Formamos el nombre del archivo
			File imageFile = new File(ruta + nombreFinal);
			System.out.println("Archivo: " + imageFile.getAbsolutePath());
			// Guardamos el archivo
			multiPart.transferTo(imageFile);
			return nombreFinal;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}

	/**
	 * Genera un String aleatorio de longitud <i>count</i>
	 * 
	 * @param count
	 * @return cadena aleatoria
	 */
	private static String randomAlphaNumeric(int count) {
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));
		}
		return builder.toString();
	}
	/**
	 * Lee un archivo 
	 * @param archivo
	 * @return cadena con el contenido 
	 * @throws IOException
	 */
	public String leerArchivo(String archivo, String ruta) throws IOException {
		File file = new File(ruta + archivo);
		return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
	}

}
