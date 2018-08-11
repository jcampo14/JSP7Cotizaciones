package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Criterio;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.CriterioServices;

@RestController
public class CriterioController {

	private static final String SERVICE_PATH = "/criterios/";
	
	@Autowired
	private CriterioServices service;
	
	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public QueryResponse<Criterio> getByEmpresa(@RequestParam("emp") String empresa){
		QueryResponse<Criterio> response = new QueryResponse<>();
		List<Criterio> resultdata = service.showByEmpresa(empresa);		
		response.setCount(resultdata.size());
		response.setData(resultdata);
		return response;		
	}
}
