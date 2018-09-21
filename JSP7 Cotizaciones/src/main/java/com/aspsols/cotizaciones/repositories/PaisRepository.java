package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Pais;
import com.aspsols.cotizaciones.model.ids.PaisId;

public interface PaisRepository extends CrudRepository<Pais, PaisId> {

	@Query("FROM Pais WHERE cEmp = :empresa")
	public List<Pais> findByEmpresa(@Param("empresa") String empresa);
}
