package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.CotSeccionesSinonimos;
import com.aspsols.cotizaciones.model.CotSeccionesSinonimosId;

public interface CotSeccionesSinonimosRepository extends CrudRepository<CotSeccionesSinonimos, CotSeccionesSinonimosId> {
	
	@Query("FROM CotSeccionesSinonimos WHERE codSeccion = :seccion and cEmp = :emp")
	public Iterable<CotSeccionesSinonimos> findBySeccion(@Param("emp") String emp, @Param("seccion") String seccion);

}
