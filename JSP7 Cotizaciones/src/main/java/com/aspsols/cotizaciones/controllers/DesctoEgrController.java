package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.DesctoEgr;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.DesctoEgrServices;

@RestController
public class DesctoEgrController {
	
	private static final String SERVICE_PATH = "/descto-egr/";

	@Autowired
	private DesctoEgrServices service;
	
	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public QueryResponse<DesctoEgr> findIvasByEmpresa(@RequestParam("emp") String empresa){
		QueryResponse<DesctoEgr> response = new QueryResponse<>();
		List<DesctoEgr> resultData = service.findIvasByEmpresa(empresa);
		response.setCount(resultData.size());
		response.setData(resultData);
		return response;
	}
}
