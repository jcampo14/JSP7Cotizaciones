package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.ParamFac;

public interface ParamFacRepository extends CrudRepository<ParamFac, String> {

	@Query(value = "FROM ParamFac WHERE cEmp = :emp")
	public List<ParamFac> findByEmpresa(@Param("emp") String empresa);
}
