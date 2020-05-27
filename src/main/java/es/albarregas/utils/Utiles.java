package es.albarregas.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class Utiles {

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
	 * @return
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
  * NULL ¿?¿?¿?¿ 
  */
	@Value("${fixmycode.ruta.archivos}")
	private String ruta;

	public String leerArchivo(String archivo) throws IOException {
		File file = new File("C:/spring-tool-curso/FixMyCode/src/main/resources/static/archivos/" + archivo);
		return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
	}

}
