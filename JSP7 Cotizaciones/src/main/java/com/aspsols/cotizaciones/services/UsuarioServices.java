package com.aspsols.cotizaciones.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Usuarios;
import com.aspsols.cotizaciones.repositories.UsuariosRepository;

@Service
public class UsuarioServices implements UserDetailsService {

	@Autowired
	private UsuariosRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		Usuarios user = repository.findByUserName(username);				
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
	}

}
