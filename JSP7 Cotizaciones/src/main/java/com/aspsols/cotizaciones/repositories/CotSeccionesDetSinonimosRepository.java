package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.CotSeccionesDetSinonimos;
import com.aspsols.cotizaciones.model.ids.CotSeccionesDetSinonimosId;

public interface CotSeccionesDetSinonimosRepository
		extends CrudRepository<CotSeccionesDetSinonimos, CotSeccionesDetSinonimosId> {

	@Query("FROM CotSeccionesDetSinonimos WHERE codSeccion = :codSeccion AND codVal = :codVal AND cEmp = :cEmp")
	public Iterable<CotSeccionesDetSinonimos> findBySeccionDetalle(@Param("codSeccion") String codSeccion,
			@Param("codVal") String codVal, @Param("cEmp") String cEmp);

	@Query("FROM CotSeccionesDetSinonimos WHERE codSeccion = :codSeccion AND idioma = :idioma" + " AND cEmp = :cEmp")
	public List<CotSeccionesDetSinonimos> findBySeccionDetalleAndIdioma(@Param("codSeccion") String codSeccion,
			@Param("idioma") String idioma, @Param("cEmp") String cEmp);

}
