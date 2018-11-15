package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.CliContacto;

public interface CliContactoRepository extends CrudRepository<CliContacto, Long> {

	@Query("FROM CliContacto c WHERE c.nit = :nit and c.cEmp = :empresa")
	public List<CliContacto> findByNit(@Param("empresa") String empresa, @Param("nit") String nit);
}
