package com.aspsols.cotizaciones.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.SucCli;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.SucCliServices;

@RestController
public class SucCliController {

	private static final String SERVICE_PATH = "/suc-cli/";
	
	@Autowired
	private SucCliServices service;
	
	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public QueryResponse<SucCli> findByNit(@RequestParam("emp") String empresa, @RequestParam("nit") String nit){
		QueryResponse<SucCli> response = new QueryResponse<>();
		List<SucCli> resultData = service.findByNit(empresa, nit);
		response.setCount(resultData.size());
		response.setData(resultData);
		return response;
	}
}
