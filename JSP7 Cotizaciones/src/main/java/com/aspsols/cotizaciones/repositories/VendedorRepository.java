package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.Vendedor;
import com.aspsols.cotizaciones.model.ids.VendedorId;

public interface VendedorRepository extends CrudRepository<Vendedor, VendedorId> {

	@Query("FROM Vendedor WHERE cEmp = :empresa AND usuario = :usuario")
	public Vendedor findByUsuario(@Param("empresa") String empresa, @Param("usuario") String usuario);
	
	@Query("FROM Vendedor WHERE cEmp = :empresa and est = 'A'")
	public List<Vendedor> findByEmpresa(@Param("empresa") String empresa);
	
	@Query("FROM Vendedor WHERE nIde = :nit AND cEmp = :empresa")
	public Vendedor findByNit(@Param("nit") String nIde, @Param("empresa") String empresa);

}
