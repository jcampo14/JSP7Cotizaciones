package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Departamento;
import com.aspsols.cotizaciones.model.ids.DepartamentoId;

public interface DepartamentoRepository extends CrudRepository<Departamento, DepartamentoId> {

	@Query("SELECT e FROM Departamento e WHERE e.cPai = :pais AND e.cEmp = :empresa")
	public List<Departamento> findByPais(@Param("empresa") String empresa, @Param("pais") String pais);
	
}
