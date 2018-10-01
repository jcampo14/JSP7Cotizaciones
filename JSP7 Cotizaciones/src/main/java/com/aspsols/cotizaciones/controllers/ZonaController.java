package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Zona;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.ZonaServices;

@RestController
public class ZonaController {
	
	private static final String SERVICE_PATH = "/zonas";

	@Autowired
	private ZonaServices services;
	
	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public QueryResponse<Zona> findByEmpresa(@RequestParam("emp") String empresa){
		List<Zona> resultData = services.findByEmpresa(empresa);
		return new QueryResponse<>(resultData, resultData.size());
	}
}
