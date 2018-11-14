package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.EmbalajeSinonimo;
import com.aspsols.cotizaciones.model.ids.EmbalajeSinonimoId;

public interface EmbalajeSinonimoRepository extends PagingAndSortingRepository<EmbalajeSinonimo, EmbalajeSinonimoId> {

	@Query("FROM EmbalajeSinonimo e WHERE e.codEmb = :codEmb AND e.cEmp = :empresa")
	public List<EmbalajeSinonimo> findByEmbalaje(@Param("empresa") String empresa, @Param("codEmb") String codEmb);
}
