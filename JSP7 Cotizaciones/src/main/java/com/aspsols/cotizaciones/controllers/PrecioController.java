package com.aspsols.cotizaciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Precio;
import com.aspsols.cotizaciones.model.ids.PrecioId;
import com.aspsols.cotizaciones.services.PrecioServices;

@RestController
public class PrecioController {

	private static final String SERVICE_PATH = "/precios";
	
	@Autowired
	private PrecioServices service;

	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public Precio findById(@RequestParam("emp") String empresa, @RequestParam("cod") String articulo,
			@RequestParam("cri") String criterio) {
		PrecioId id = new PrecioId(empresa, articulo, criterio);
		return service.findById(id);
	}

}
