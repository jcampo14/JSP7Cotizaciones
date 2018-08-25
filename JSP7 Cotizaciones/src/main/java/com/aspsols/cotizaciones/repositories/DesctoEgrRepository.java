package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspsols.cotizaciones.model.DesctoEgr;
import com.aspsols.cotizaciones.model.ids.DesctoEgrId;

public interface DesctoEgrRepository extends CrudRepository<DesctoEgr, DesctoEgrId> {
	
	@Query(value = "FROM DesctoEgr WHERE usoImp = 'V' AND tipoImp = 'IVA' AND cEmp = :pcEmp")
	public Iterable<DesctoEgr> findIvasByEmpresa(@Param("pcEmp") String empresa);

}
