package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.IncotermFacCostosAdic;
import com.aspsols.cotizaciones.model.IncotermFacCostosAdicId;

public interface IncotermFacCostosAdicRepository extends CrudRepository<IncotermFacCostosAdic, IncotermFacCostosAdicId> {
	
	@Query("FROM IncotermFacCostosAdic WHERE idIncoterm = :incoterm AND cEmp = :emp")
	public Iterable<IncotermFacCostosAdic> findByIncoterm(@Param("incoterm") String incoterm, @Param("emp") String emp);

}
