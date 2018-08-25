package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.CotSeccionesDet;
import com.aspsols.cotizaciones.model.ids.CotSeccionesDetId;

public interface CotSeccionesDetRepository extends CrudRepository<CotSeccionesDet, CotSeccionesDetId> {

	@Query("FROM CotSeccionesDet WHERE codSeccion = :seccion AND cEmp = :emp")
	public Iterable<CotSeccionesDet> findBySeccion(@Param("seccion") String seccion, @Param("emp") String emp);
}
