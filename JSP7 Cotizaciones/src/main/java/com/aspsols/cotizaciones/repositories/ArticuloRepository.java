package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Articulo;
import com.aspsols.cotizaciones.model.ArticuloId;

public interface ArticuloRepository extends CrudRepository<Articulo, ArticuloId> {

	@Query("FROM Articulo WHERE cEmp = :empresa")
	public Iterable<Articulo> findByEmpresa(@Param("empresa") String empresa);
}
