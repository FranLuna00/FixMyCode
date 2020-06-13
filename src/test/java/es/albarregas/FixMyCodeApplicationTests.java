package es.albarregas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import es.albarregas.controller.AdminController;
import es.albarregas.controller.HomeController;
import es.albarregas.controller.PublicacionesController;
import es.albarregas.controller.UsuariosController;

@SpringBootTest
@AutoConfigureMockMvc
class FixMyCodeApplicationTests {
	
	@Autowired
	private HomeController controladorInicio;
	
	@Autowired
	private AdminController controladorAdmin;
	
	@Autowired
	private PublicacionesController controladorPublicaciones;
	
	@Autowired
	private UsuariosController controladorUsuarios;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		
	}
	
	@Test
	void testPaginaInicio () throws Exception {
		mockMvc.perform(get("/home")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Publicaciones más populares")));
	}
	
	@Test
	void testDetallePublicacion () throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .get("/publicaciones/{id}", 14)
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk());
	}

	@Test
	void testControladoresDisponibles () {

		assertThat(controladorInicio).isNotNull();

		assertThat(controladorAdmin).isNotNull();

		assertThat(controladorPublicaciones).isNotNull();

		assertThat(controladorUsuarios).isNotNull();
		
	}
	
}
