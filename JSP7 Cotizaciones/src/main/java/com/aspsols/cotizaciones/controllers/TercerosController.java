package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Terceros;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.TercerosServices;

@RestController
public class TercerosController {

	private static final String SERVICE_PATH = "/terceros/";
	
	@Autowired
	private TercerosServices service;
	
	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public QueryResponse<Terceros> findByEmpresa(@RequestParam("emp") String empresa){
		QueryResponse<Terceros> response = new QueryResponse<>();
		List<Terceros> resultData = service.getTerceros(empresa);
		response.setCount(resultData.size());
		response.setData(resultData);
		return response;
	}
}