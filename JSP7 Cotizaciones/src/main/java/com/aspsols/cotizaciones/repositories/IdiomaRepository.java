package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Idioma;
import com.aspsols.cotizaciones.model.ids.IdiomaId;

public interface IdiomaRepository extends CrudRepository<Idioma, IdiomaId>{
	
	@Query("FROM Idioma WHERE cEmp = :emp")
	public Iterable<Idioma> findByEmpresa(@Param("emp") String emp);
}
