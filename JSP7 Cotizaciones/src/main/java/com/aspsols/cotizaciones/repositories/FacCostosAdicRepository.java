package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.FacCostosAdic;
import com.aspsols.cotizaciones.model.FacCostosAdicId;

public interface FacCostosAdicRepository extends CrudRepository<FacCostosAdic, FacCostosAdicId> {
	
	/* Nota: el from del select es el nombre de la clase JAVA generada como modelo */
	@Query("FROM FacCostosAdic WHERE C_EMP = :emp")
	public Iterable<FacCostosAdic> findByEmpresa(@Param("emp") String emp);
}
