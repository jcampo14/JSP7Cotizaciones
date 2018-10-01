package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Zona;
import com.aspsols.cotizaciones.model.ids.ZonaId;

public interface ZonaRepository extends CrudRepository<Zona, ZonaId>{

	@Query("FROM Zona WHERE tipo = 'D' AND cEmp = :empresa")
	public List<Zona> findByEmpresa(@Param("empresa") String empresa);
}
