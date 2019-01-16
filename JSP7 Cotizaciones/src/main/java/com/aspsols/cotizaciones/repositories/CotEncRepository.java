package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.CotEnc;
import com.aspsols.cotizaciones.model.ids.CotEncId;
import com.aspsols.cotizaciones.querys.CotizacionCountQuery;
import com.aspsols.cotizaciones.querys.CotizacionQuery;

public interface CotEncRepository extends CrudRepository<CotEnc, CotEncId> {

	@Query("FROM CotEnc WHERE cEmp = :empresa AND cVen = :vendedor")
	public List<CotEnc> findByEmpresaAndVendedor(@Param("empresa") String empresa, @Param("vendedor") String vendedor);

	@Query("SELECT c FROM CotEnc c WHERE perCt BETWEEN :perIni AND :perFin AND cEmp = :empresa")
	public Page<CotEnc> findByPeriod(@Param("perIni") String perIni, @Param("perFin") String perFin,
			@Param("empresa") String empresa, Pageable pageable);
	
	@Query("SELECT c FROM CotEnc c WHERE nIde = :cliente AND cEmp = :empresa")
	public Page<CotEnc> findByCliente(@Param("cliente") String cliente, @Param("empresa") String empresa, Pageable pageable);
	
	@Query("SELECT c FROM CotEnc c WHERE cot = :numeroCot AND cEmp = :empresa")
	public Page<CotEnc> findByNumero(@Param("numeroCot") String numeroCot, @Param("empresa") String empresa, Pageable pageable);
	
	@Query("SELECT c FROM CotEnc c WHERE cot = :cot AND per = :periodo AND cAgr = :agencia AND cEmp = :empresa")
	public Page<CotEnc> findByNumeroCot(@Param("empresa") String empresa, @Param("cot") String cot,
			@Param("agencia") String agencia, @Param("periodo") String periodo, Pageable pageable);

	@Query("SELECT new com.aspsols.cotizaciones.querys.CotizacionCountQuery(cEmp, per, cAgr, cot, count(rev)) "
			+ " FROM CotEnc" + " WHERE cVen = :vendedor AND cEmp = :empresa" + " GROUP BY cEmp, per, cAgr, cot")
	public List<CotizacionCountQuery> findByEmpresaAndVendedorGroupBy(@Param("empresa") String empresa,
			@Param("vendedor") String vendedor);

	@Query("SELECT new com.aspsols.cotizaciones.querys.CotizacionQuery(c.cEmp, c.per, c.cAgr, c.cot, c.rev, c.emi, c.cliente) "
			+ " FROM CotEnc c"
			+ " WHERE c.cot = :numeroCot AND c.cAgr = :agencia AND c.per = :periodo AND c.cEmp = :empresa")
	public List<CotizacionQuery> findByCot(@Param("empresa") String empresa, @Param("agencia") String agencia,
			@Param("periodo") String periodo, @Param("numeroCot") String numeroCot);

	@Query("FROM CotEnc WHERE cot = :numeroCot AND rev = :revision AND cAgr = :agencia AND per = :periodo AND cEmp = :empresa")
	public CotEnc findByCotAndRev(@Param("empresa") String empresa, @Param("agencia") String agencia,
			@Param("periodo") String periodo, @Param("numeroCot") String numeroCot,
			@Param("revision") Integer revision);

}
