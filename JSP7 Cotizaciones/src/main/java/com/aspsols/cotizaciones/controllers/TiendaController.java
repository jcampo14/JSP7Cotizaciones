package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Tienda;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.TiendaServices;

@RestController
public class TiendaController {

	private static final String SERVICE_PATH = "/tienda";
	
	@Autowired
	private TiendaServices service;
	
	@RequestMapping(method=RequestMethod.GET, path=SERVICE_PATH)
	public QueryResponse<Tienda> getProductsService(@RequestParam("emp") String emp,
													@RequestParam("rama") String rama,
													@RequestParam("imgprincipal") String imgprincipal){
		QueryResponse<Tienda> response = new QueryResponse<>();
		List<Tienda> list = service.getProducts(emp, rama, imgprincipal);
		response.setCount(list.size());
		response.setData(list);
		return response;
	}
}
