package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.SucCli;
import com.aspsols.cotizaciones.model.ids.SucCliId;

public interface SucCliRepository extends CrudRepository<SucCli, SucCliId>{
	
	@Query("FROM SucCli WHERE nIde = :nit AND cEmp = :emp")
	public Iterable<SucCli> findByNit(@Param("emp") String empresa, @Param("nit") String nit);

}
