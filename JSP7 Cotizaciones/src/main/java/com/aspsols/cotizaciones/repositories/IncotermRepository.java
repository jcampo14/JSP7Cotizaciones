package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Incoterm;
import com.aspsols.cotizaciones.model.IncotermId;

public interface IncotermRepository extends CrudRepository<Incoterm, IncotermId> {
	
	/* Nota: el from del select es el nombre de la clase JAVA generada como modelo */
	@Query("FROM Incoterm WHERE cEmp = :emp")
	public Iterable<Incoterm> findByEmpresa(@Param("emp") String emp);
	
}
