package es.albarregas.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuración de Spring Security
 * @author Fran Luna
 *
 */
@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
	/**
	 * Data source declarado en application.properties
	 */
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder encoder;
	/**
	 * Encriptación de contraseñas
	 * @return
	 */
    @Bean
    public PasswordEncoder passwordEncoder() {
    	 return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
	/**
	 * Consultas para recoger usuarios de la base de datos
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT username, passwd, enabled FROM usuarios WHERE username=?").passwordEncoder(encoder)
				.authoritiesByUsernameQuery("SELECT u.username, p.rol FROM usuarios AS u INNER JOIN perfiles AS p "
						+ "ON u.idPerfil = p.id WHERE u.username=?");
	}
	/**
	 * Configura el acceso a diferentes url dado el rol del usuario
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	      .csrf().disable();
		http.authorizeRequests() // Los recursos estáticos no requieren autenticación
				.antMatchers("/bootstrap/**", "/css/**", "/img/**", "/js/**").permitAll()
				// Las vistas públicas no requieren autenticación
				.antMatchers("/", "/home", "/usuarios/**", "/logout", "/publicaciones/**").permitAll()
				//Vista de gestión de usuarios requiere admin
				.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
				.antMatchers("/publicaciones/nueva", "/publicaciones/valorar", "/publicaciones/respuesta").hasAnyAuthority("REGISTRADO", "ADMIN")
				// Todas las demás URLs de la Aplicación requieren autenticación
				.anyRequest().authenticated()
				// El formulario de Login no requiere autenticacion
				.and().formLogin().loginPage("/login").permitAll();
		http
			.logout()
			.logoutSuccessUrl("/");
		
		http.formLogin().defaultSuccessUrl("/", true);
	}

}
