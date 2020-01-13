package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Municipio;
import com.aspsols.cotizaciones.repositories.MunicipioRepository;
import com.aspsols.cotizaciones.responses.QueryResponse;

@RestController
public class MunicipioController {

	@Autowired
	private MunicipioRepository municipioRepository;

	@GetMapping(value = "/municipios")
	public QueryResponse<Municipio> findByDepartamento(@RequestParam("empresa") String empresa,
			@RequestParam("pais") String pais, @RequestParam("departamento") String departamento) {
		List<Municipio> queryData = municipioRepository.findByDepartamento(empresa, pais, departamento);
		return new QueryResponse<>(queryData, queryData.size());
	}
}
