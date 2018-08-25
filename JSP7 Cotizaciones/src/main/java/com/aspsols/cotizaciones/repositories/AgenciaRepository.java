package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Agencia;
import com.aspsols.cotizaciones.model.ids.AgenciaId;

public interface AgenciaRepository extends CrudRepository<Agencia, AgenciaId>{

	@Query("FROM Agencia WHERE cEmp = :emp")
	public Iterable<Agencia> findByEmpresa(@Param("emp") String empresa);
}
