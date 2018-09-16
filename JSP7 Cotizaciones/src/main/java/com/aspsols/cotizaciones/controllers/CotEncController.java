package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.CotEnc;
import com.aspsols.cotizaciones.querys.CotizacionQuery;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.CotEncServices;

@RestController
public class CotEncController {
	

	@Autowired
	private CotEncServices service;

	@RequestMapping(method = RequestMethod.GET, path = "/cot-enc/")
	public QueryResponse<CotEnc> findByEmpresaAndVendedor(@RequestParam("emp") String empresa, @RequestParam("age") String agencia,
			@RequestParam("per") String periodo, @RequestParam("numeroCot") String numeroCot, @RequestParam("rev") Integer revision){
		QueryResponse<CotEnc> response = new QueryResponse<>();		
		List<CotEnc> resultData = service.findByCotAndRev(empresa, agencia, periodo, numeroCot, revision);
		response.setCount(resultData.size());
		response.setData(resultData);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/cot-enc-list/")
	public QueryResponse<CotizacionQuery> findByEmpresaAndVendedorGroupBy(@RequestParam("emp") String empresa, @RequestParam("ven") String vendedor){
		QueryResponse<CotizacionQuery> response = new QueryResponse<>();
		List<CotizacionQuery> resultData = service.findByEmpresaAndVendedorGroupBy(empresa, vendedor);
		response.setCount(resultData.size());
		response.setData(resultData);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/cot-enc-rev/")
	public QueryResponse<CotEnc> findByCot(@RequestParam("emp") String empresa, @RequestParam("age") String agencia,
			@RequestParam("per") String periodo, @RequestParam("numeroCot") String numeroCot){
		QueryResponse<CotEnc> response = new QueryResponse<>();
		List<CotEnc> resultData = service.findByCot(empresa, agencia, periodo, numeroCot);
		response.setCount(resultData.size());
		response.setData(resultData);
		return response;
	}
}
