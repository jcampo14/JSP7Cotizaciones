package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Mercado;
import com.aspsols.cotizaciones.model.ids.MercadoId;

public interface MercadoRepository extends CrudRepository<Mercado, MercadoId> {

	@Query("FROM Mercado WHERE tipo = 'D' AND cEmp = :empresa")
	public List<Mercado> findByEmpresa(@Param("empresa") String empresa);
}
