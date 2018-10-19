package com.aspsols.cotizaciones.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	public WebSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}	
	
	@Bean
	public JSP7PasswordEncoder jsp7PasswordEncoder() {
		return new JSP7PasswordEncoder();
	}		

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		/*
		 * 1. Se desactiva el uso de cookies
		 * 2. Se activa la configuración CORS con los valores por defecto
		 * 3. Se desactiva el filtro CSRF
		 * 4. Se indica que el login no requiere autenticación
		 * 5. Se indica que el resto de URLs esten securizadas
		 * 6. Se indica que los frameoptions como SAME_ORIGIN para que el menu pueda usar los tabs.
		 */
		httpSecurity.headers().frameOptions().sameOrigin();
		JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(authenticationManager());
		authenticationFilter.setFilterProcessesUrl(Constants.LOGIN_URL);
		httpSecurity
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.cors().and()
			.csrf().disable()
			.authorizeRequests()//.antMatchers(HttpMethod.POST, Constants.LOGIN_URL).permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/css/**").permitAll()				
			.antMatchers("/**.html").permitAll()
			.antMatchers("/favicon.ico").permitAll()
			.antMatchers("/fonts/**").permitAll()
			.antMatchers("/structure/**").permitAll()
			.antMatchers("/img/**").permitAll()
			.antMatchers("/companies").permitAll()
			.anyRequest().authenticated().and()
				.addFilter(authenticationFilter)
				.addFilter(new JWTAuthorizationFilter(authenticationManager()));
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Se define la clase que recupera los usuarios y el algoritmo para procesar las passwords
		auth.userDetailsService(userDetailsService).passwordEncoder(jsp7PasswordEncoder());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}