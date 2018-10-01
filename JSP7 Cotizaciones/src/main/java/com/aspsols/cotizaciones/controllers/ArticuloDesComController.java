package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.ArticuloDesCom;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.ArticuloDesComServices;

@RestController
public class ArticuloDesComController {

	private static final String SERVICE_PATH = "/articulo-descripcion";

	@Autowired
	private ArticuloDesComServices services;

	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public QueryResponse<ArticuloDesCom> findByArticuloAndIdioma(@RequestParam("emp") String empresa,
			@RequestParam("cod") String articulo, @RequestParam("idioma") String idioma) {
		List<ArticuloDesCom> resultData = services.findByArticuloAndIdioma(empresa, articulo, idioma);
		return new QueryResponse<>(resultData, resultData.size());
	}
}
