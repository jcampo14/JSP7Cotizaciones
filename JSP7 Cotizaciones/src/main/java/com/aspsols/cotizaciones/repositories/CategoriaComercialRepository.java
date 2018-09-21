package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.CategoriaComercial;
import com.aspsols.cotizaciones.model.ids.CategoriaComercialId;

public interface CategoriaComercialRepository extends CrudRepository<CategoriaComercial, CategoriaComercialId> {

	@Query("FROM CategoriaComercial WHERE codCatPadre is null AND cEmp = :empresa")
	public List<CategoriaComercial> findByRoot(@Param("empresa") String empresa);
}
