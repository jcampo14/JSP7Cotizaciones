package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.CotEnc;
import com.aspsols.cotizaciones.model.ids.CotEncId;
import com.aspsols.cotizaciones.querys.CotizacionQuery;

public interface CotEncRepository extends CrudRepository<CotEnc, CotEncId> {

	@Query("FROM CotEnc WHERE cEmp = :empresa AND cVen = :vendedor")
	public List<CotEnc> findByEmpresaAndVendedor(@Param("empresa") String empresa, @Param("vendedor") String vendedor);

	@Query("SELECT new com.aspsols.cotizaciones.querys.CotizacionQuery(cEmp, per, cAgr, cot) " + " FROM CotEnc"
			+ " WHERE cEmp = :empresa AND cVen = :vendedor" + " GROUP BY cEmp, per, cAgr, cot")
	public List<CotizacionQuery> findByEmpresaAndVendedorGroupBy(@Param("empresa") String empresa,
			@Param("vendedor") String vendedor);
	
	@Query("FROM CotEnc WHERE cot = :numeroCot AND cAgr = :agencia AND per = :periodo AND cEmp = :empresa")
	public List<CotEnc> findByCot(@Param("empresa") String empresa,@Param("agencia") String agencia,
			@Param("periodo") String periodo, @Param("numeroCot") String numeroCot);
	
	@Query("FROM CotEnc WHERE cot = :numeroCot AND rev = :revision AND cAgr = :agencia AND per = :periodo AND cEmp = :empresa")
	public List<CotEnc> findByCotAndRev(@Param("empresa") String empresa,@Param("agencia") String agencia,
			@Param("periodo") String periodo, @Param("numeroCot") String numeroCot, @Param("revision") Integer revision);

}
