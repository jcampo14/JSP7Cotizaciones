package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.ArticuloDesCom;

public interface ArticuloDesComRepository extends PagingAndSortingRepository<ArticuloDesCom, Long> {

	@Query("FROM ArticuloDesCom WHERE cod = :articulo AND cEmp = :empresa "
			+ "AND ((:idioma is null AND idioma is null) OR (:idioma is null AND idioma = :idioma))")
	public List<ArticuloDesCom> findByArticuloAndIdioma(@Param("empresa") String empresa,
			@Param("articulo") String articulo, @Param("idioma") String idioma);
	
	@Query("FROM ArticuloDesCom WHERE cod = :articulo AND cEmp = :empresa ")
	public List<ArticuloDesCom> findByArticulo(@Param("empresa") String empresa,
			@Param("articulo") String articulo);

}
