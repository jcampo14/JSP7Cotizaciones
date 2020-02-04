package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.ViewClientes;

public interface ViewClientesRepository extends CrudRepository<ViewClientes, Long> {

	@Query("SELECT e FROM ViewClientes e WHERE e.codEmpresa = :empresa")
	public List<ViewClientes> getByEmpresa(@Param("empresa") String empresa);
}
