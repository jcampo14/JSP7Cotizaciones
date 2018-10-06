package com.aspsols.cotizaciones.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Articulo;
import com.aspsols.cotizaciones.model.ids.ArticuloId;

public interface ArticuloRepository extends PagingAndSortingRepository<Articulo, ArticuloId> {

	@Query("FROM Articulo WHERE rama = 'D' AND (cod LIKE '%'||:filtro||'%' OR nom LIKE '%'||:filtro||'%') AND fact = 'S' "
			+ " AND cEmp = :empresa")
	public Iterable<Articulo> findByEmpresa(@Param("empresa") String empresa, @Param("filtro") String filtro);
	
	@Query("SELECT A FROM Articulo A WHERE (cod LIKE '%'||:filtro||'%' OR nom LIKE '%'||:filtro||'%') AND fact = 'S' "
			+ " AND rama = 'D' AND cEmp = :empresa")
	public Page<Articulo> findByEmpresa(@Param("empresa") String empresa, @Param("filtro") String filtro, Pageable page);
	
}
