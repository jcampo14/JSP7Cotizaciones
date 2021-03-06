package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.CotEnc;
import com.aspsols.cotizaciones.querys.CotizacionCountQuery;
import com.aspsols.cotizaciones.querys.CotizacionQuery;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.CotEncServices;

@RestController
public class CotEncController {

	@Autowired
	private CotEncServices service;

	@RequestMapping(method = RequestMethod.GET, path = "/cot-encByPeriodo")
	public QueryResponse<CotEnc> findByPeriod(@RequestParam("perIni") String perIni,
			@RequestParam("perFin") String perFin, @RequestParam("emp") String empresa, @RequestParam("page") int page,
			@RequestParam("size") int size, @RequestParam("order") String order) {
		Page<CotEnc> pageResult = service.findByPeriod(perIni, perFin, empresa, page, size, order);
		return new QueryResponse<>(pageResult.getContent(), pageResult.getTotalElements());
	}

	@RequestMapping(method = RequestMethod.GET, path = "/cot-encByCliente")
	public QueryResponse<CotEnc> findByCliente(@RequestParam("emp") String empresa,
			@RequestParam("cliente") String cliente, @RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("order") String order) {
		Page<CotEnc> pageResult = service.findByCliente(cliente, empresa, page, size, order);
		return new QueryResponse<>(pageResult.getContent(), pageResult.getTotalElements());
	}

	@RequestMapping(method = RequestMethod.GET, path = "/cot-encByNumero")
	public QueryResponse<CotEnc> findByNumero(@RequestParam("emp") String empresa,
			@RequestParam("numeroCot") String numeroCot, @RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("order") String order) {
		Page<CotEnc> pageResult = service.findByNumero(numeroCot, empresa, page, size, order);
		return new QueryResponse<>(pageResult.getContent(), pageResult.getTotalElements());
	}

	@RequestMapping(method = RequestMethod.GET, path = "/cot-enc")
	public CotEnc findByEmpresaAndVendedor(@RequestParam("emp") String empresa, @RequestParam("age") String agencia,
			@RequestParam("per") String periodo, @RequestParam("numeroCot") String numeroCot,
			@RequestParam("rev") Integer revision) {
		CotEnc resultData = service.findByCotAndRev(empresa, agencia, periodo, numeroCot, revision);
		return resultData;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/cot-enc-list")
	public QueryResponse<CotizacionCountQuery> findByEmpresaAndVendedorGroupBy(@RequestParam("emp") String empresa,
			@RequestParam("ven") String vendedor) {
		QueryResponse<CotizacionCountQuery> response = new QueryResponse<>();
		List<CotizacionCountQuery> resultData = service.findByEmpresaAndVendedorGroupBy(empresa, vendedor);
		response.setCount(resultData.size());
		response.setData(resultData);
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/cot-enc-rev")
	public QueryResponse<CotizacionQuery> findByCot(@RequestParam("emp") String empresa,
			@RequestParam("age") String agencia, @RequestParam("per") String periodo,
			@RequestParam("numeroCot") String numeroCot) {
		QueryResponse<CotizacionQuery> response = new QueryResponse<>();
		List<CotizacionQuery> resultData = service.findByCot(empresa, agencia, periodo, numeroCot);
		response.setCount(resultData.size());
		response.setData(resultData);
		return response;
	}
}
