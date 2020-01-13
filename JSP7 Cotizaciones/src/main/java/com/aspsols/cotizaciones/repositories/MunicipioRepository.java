package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Municipio;
import com.aspsols.cotizaciones.model.ids.MunicipioId;

public interface MunicipioRepository extends CrudRepository<Municipio, MunicipioId> {

	@Query("SELECT e FROM Municipio e WHERE e.cPai = :pais AND e.cDpto = :departamento AND e.cEmp = :empresa")
	public List<Municipio> findByDepartamento(@Param("empresa") String empresa, @Param("pais") String pais,
			@Param("departamento") String departamento);
}
