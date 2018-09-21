package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Pais;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.PaisServices;

@RestController
public class PaisController {
	
	private static final String SERVICE_PATH = "/paises";

	@Autowired
	private PaisServices services;
	
	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public QueryResponse<Pais> findByEmpresa(@RequestParam("emp") String empresa){
		List<Pais> resultData = services.findByEmpresa(empresa);
		return new QueryResponse<>(resultData, resultData.size());
	}
}
