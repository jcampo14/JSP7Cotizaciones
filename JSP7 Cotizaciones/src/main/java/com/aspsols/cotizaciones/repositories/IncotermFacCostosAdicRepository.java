package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.IncotermFacCostosAdic;
import com.aspsols.cotizaciones.model.ids.IncotermFacCostosAdicId;

public interface IncotermFacCostosAdicRepository extends CrudRepository<IncotermFacCostosAdic, IncotermFacCostosAdicId> {
	
	@Query("FROM IncotermFacCostosAdic WHERE idIncoterm = :incoterm AND cEmp = :emp")
	public Iterable<IncotermFacCostosAdic> findByIncoterm(@Param("incoterm") String incoterm, @Param("emp") String emp);

	@Query("FROM IncotermFacCostosAdic WHERE idIncoterm = :incoterm AND facCostosAdic.moneda = :moneda AND cEmp = :empresa")
	public List<IncotermFacCostosAdic> findByIncotermAndMoneda(@Param("incoterm") String incoterm, @Param("moneda") String moneda,
			@Param("empresa") String empresa);
	
}
