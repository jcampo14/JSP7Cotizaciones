package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Parametros;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.ParametrosService;

@RestController
public class ParametrosController {
	
	private static final String SERVICE_PATH = "/companies";

	@Autowired
	private ParametrosService service;
	
	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public QueryResponse<Parametros> get(){
		QueryResponse<Parametros> response = new QueryResponse<>();
		List<Parametros> resultData = service.findAll();
		response.setCount(resultData.size());
		response.setData(resultData);
		return response;
	}
}
