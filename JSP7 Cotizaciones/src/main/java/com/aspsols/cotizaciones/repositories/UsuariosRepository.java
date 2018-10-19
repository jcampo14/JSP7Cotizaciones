package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Usuarios;

public interface UsuariosRepository extends CrudRepository<Usuarios, String> {
	
	@Query("FROM Usuarios WHERE username = :username")
	public Usuarios findByUserName(@Param("username") String username);

}
