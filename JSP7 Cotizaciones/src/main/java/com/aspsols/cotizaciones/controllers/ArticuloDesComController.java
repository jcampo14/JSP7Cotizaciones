package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.ArticuloDesCom;
import com.aspsols.cotizaciones.model.lists.ArticuloDesComList;
import com.aspsols.cotizaciones.responses.ProcessResponse;
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

	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH + "ByCod")
	public QueryResponse<ArticuloDesCom> findByArticulo(@RequestParam("emp") String empresa,
			@RequestParam("cod") String articulo) {
		List<ArticuloDesCom> resultData = services.findByArticulo(empresa, articulo);
		return new QueryResponse<>(resultData, resultData.size());
	}

	@RequestMapping(method = RequestMethod.POST, path = SERVICE_PATH)
	public ProcessResponse<ArticuloDesCom> create(@RequestBody ArticuloDesCom body) {
		services.create(body);
		return new ProcessResponse<>(true, "Insertado exitosamente.");
	}

	@RequestMapping(method = RequestMethod.PUT, path = SERVICE_PATH)
	public ProcessResponse<ArticuloDesCom> update(@RequestBody ArticuloDesCom body) {
		if (services.update(body)) {
			return new ProcessResponse<>(true, "Editado exitosamente.");
		} else {
			return new ProcessResponse<>(false, "No existe el registro para editar.");
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, path = SERVICE_PATH)
	public ProcessResponse<ArticuloDesCom> delete(@RequestBody ArticuloDesComList body) {
		services.delete(body);
		return new ProcessResponse<>(true, "Borrado exitosamente.");
	}

}
