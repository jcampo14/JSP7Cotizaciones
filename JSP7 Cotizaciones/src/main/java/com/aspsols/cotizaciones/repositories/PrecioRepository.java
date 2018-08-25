package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Precio;
import com.aspsols.cotizaciones.model.ids.PrecioId;

public interface PrecioRepository extends CrudRepository<Precio, PrecioId>{
	
	@Query("FROM Precio WHERE cod = :articulo AND cEmp = :emp")
	public Iterable<Precio> findByArticulo(@Param("emp") String empresa, @Param("articulo") String cod);
		

}
