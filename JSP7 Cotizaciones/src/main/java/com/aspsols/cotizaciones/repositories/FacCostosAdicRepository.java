package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.FacCostosAdic;
import com.aspsols.cotizaciones.model.ids.FacCostosAdicId;

public interface FacCostosAdicRepository extends CrudRepository<FacCostosAdic, FacCostosAdicId> {

	/*
	 * Nota: el from del select es el nombre de la clase JAVA generada como modelo
	 */
	@Query("FROM FacCostosAdic WHERE cEmp = :emp")
	public Iterable<FacCostosAdic> findByEmpresa(@Param("emp") String emp);

	@Query("SELECT e FROM FacCostosAdic e WHERE moneda = :pMoneda AND cEmp = :pEmp")
	public List<FacCostosAdic> findByMoneda(@Param("pMoneda") String moneda, @Param("pEmp") String empresa);
}
