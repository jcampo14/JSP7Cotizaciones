package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Departamento;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.DepartamentoServices;

@RestController
public class DepartamentoController {

	@Autowired
	private DepartamentoServices departamentoServices;

	@GetMapping(value = "/departamentos")
	public QueryResponse<Departamento> findByPais(@RequestParam("empresa") String empresa,
			@RequestParam("pais") String pais) {
		List<Departamento> queryData = departamentoServices.findByPais(empresa, pais);
		return new QueryResponse<>(queryData, queryData.size());
	}
}
