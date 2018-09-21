package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Articulo;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.ArticuloServices;

@RestController
public class ArticuloController {

	private static final String SERVICE_PATH = "/articulos";

	@Autowired
	private ArticuloServices service;

	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH)
	public QueryResponse<Articulo> obtener(@RequestParam("emp") String empresa, @RequestParam("filter") String filtro) {
		QueryResponse<Articulo> response = new QueryResponse<>();
		List<Articulo> resultdata = service.showByEmpresa(empresa, filtro);
		response.setCount(resultdata.size());
		response.setData(resultdata);
		return response;
	};

}
