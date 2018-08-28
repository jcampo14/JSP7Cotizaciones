package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Embalaje;
import com.aspsols.cotizaciones.model.Incoterm;
import com.aspsols.cotizaciones.model.ids.EmbalajeId;

public interface EmbalajeRepository extends CrudRepository<Embalaje, EmbalajeId>{
	
	@Query("FROM Embalaje WHERE cEmp = :cemp")
	public Iterable<Embalaje> findByEmpresa(@Param("cemp") String cemp);
}
