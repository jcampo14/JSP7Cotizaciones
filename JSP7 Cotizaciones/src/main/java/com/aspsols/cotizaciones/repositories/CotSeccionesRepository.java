package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.CotSecciones;
import com.aspsols.cotizaciones.model.ids.CotSeccionesId;

public interface CotSeccionesRepository extends CrudRepository<CotSecciones, CotSeccionesId> {
	
	@Query(value = "FROM CotSecciones WHERE cEmp = :emp")
	public Iterable<CotSecciones> findByEmpresa(@Param("emp") String emp);

}
