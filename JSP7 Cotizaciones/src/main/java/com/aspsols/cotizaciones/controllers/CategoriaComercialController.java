package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.CategoriaComercial;
import com.aspsols.cotizaciones.responses.MenuResponse;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.services.CategoriaComercialServices;

@RestController
public class CategoriaComercialController {
	
	private static final String SERVICE_PATH = "/categorias-comerciales";

	@Autowired
	private CategoriaComercialServices services;
	
	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public MenuResponse<CategoriaComercial> findByRoot(@RequestParam("emp") String empresa){
		List<CategoriaComercial> resultData = services.findByRoot(empresa);
		return new MenuResponse<>(resultData);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = SERVICE_PATH)
	public ProcessResponse<CategoriaComercial> create(@RequestBody CategoriaComercial body){
		services.create(body);
		return new ProcessResponse<>(true, "Agregado exitosamente.");
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = SERVICE_PATH)
	public ProcessResponse<CategoriaComercial> update(@RequestBody CategoriaComercial body){
		services.update(body);
		return new ProcessResponse<>(true, "Editado exitosamente.");
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = SERVICE_PATH)
	public ProcessResponse<CategoriaComercial> delete(@RequestBody CategoriaComercial body){
		services.delete(body);
		return new ProcessResponse<>(true, "Borrado exitosamente.");
	}
}
