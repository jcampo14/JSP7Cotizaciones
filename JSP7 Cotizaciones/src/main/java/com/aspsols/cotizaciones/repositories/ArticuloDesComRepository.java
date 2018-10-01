package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.ArticuloDesCom;

public interface ArticuloDesComRepository extends CrudRepository<ArticuloDesCom, Long> {

	@Query("FROM ArticuloDesCom WHERE cEmp = :empresa AND cod = :articulo "
			+ "AND ((:idioma is null) OR (:idioma is null AND idioma = :idioma))")
	public List<ArticuloDesCom> findByArticuloAndIdioma(@Param("empresa") String empresa,
			@Param("articulo") String articulo, @Param("idioma") String idioma);

}
