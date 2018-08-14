package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Cliente;
import com.aspsols.cotizaciones.model.ClienteId;

public interface ClienteRepository extends CrudRepository<Cliente, ClienteId> {
	
	@Query("FROM Cliente WHERE cEmp = :emp")
	public Iterable<Cliente> findByEmpresa(@Param("emp") String empresa);

}
