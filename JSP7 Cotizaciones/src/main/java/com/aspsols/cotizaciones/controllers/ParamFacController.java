package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.ParamFac;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.ParamFacServices;

@RestController
public class ParamFacController {
	
	private static final String SERVICE_PATH = "/param-fac/";

	@Autowired
	private ParamFacServices service;

	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public QueryResponse<ParamFac> findByEmpresa(@RequestParam("emp") String empresa) {
		QueryResponse<ParamFac> response = new QueryResponse<>();
		List<ParamFac> resultData = service.findByEmpresa(empresa);
		response.setCount(resultData.size());
		response.setData(resultData);
		return response;
	}
}
