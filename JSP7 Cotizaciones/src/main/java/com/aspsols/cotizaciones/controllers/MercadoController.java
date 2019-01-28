package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Mercado;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.MercadoServices;

@RestController
public class MercadoController {

	private static final String SERVICE_PATH = "/mercados";

	@Autowired
	private MercadoServices services;

	@GetMapping(value = SERVICE_PATH)
	public QueryResponse<Mercado> findByEmpresa(@RequestParam("emp") String empresa) {
		List<Mercado> resultData = services.findByEmpresa(empresa);
		return new QueryResponse<>(resultData, resultData.size());
	}

}
