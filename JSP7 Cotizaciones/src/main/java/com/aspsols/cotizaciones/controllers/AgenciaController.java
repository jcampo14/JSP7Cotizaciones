package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Agencia;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.AgenciaServices;

@RestController
public class AgenciaController {
	
	private static final String SERVICE_PATH = "/agencias";

	@Autowired
	private AgenciaServices service;
	
	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public QueryResponse<Agencia> findByEmpresa(@RequestParam("emp") String empresa){
		QueryResponse<Agencia> response = new QueryResponse<>();
		List<Agencia> resultData = service.findByEmpresa(empresa);
		response.setCount(resultData.size());
		response.setData(resultData);
		return response;
	}
}
