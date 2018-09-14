package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Vendedor;
import com.aspsols.cotizaciones.model.ids.VendedorId;

public interface VendedorRepository extends CrudRepository<Vendedor, VendedorId> {

	@Query("FROM Vendedor WHERE cEmp = :empresa AND usuario = :usuario")
	public Vendedor findByUsuario(@Param("empresa") String empresa, @Param("usuario") String usuario);

}
