package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Criterio;
import com.aspsols.cotizaciones.model.ids.CriterioId;

public interface CriterioRepository extends CrudRepository<Criterio, CriterioId> {
	
	@Query("FROM Criterio WHERE act = 'S' AND cEmp = :emp")
	public Iterable<Criterio> findByEmpresa(@Param("emp") String empresa);
}
