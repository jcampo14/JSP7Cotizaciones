package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.EmbalajeSinonimo;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.EmbalajeSinonimoServices;

@RestController
public class EmbalajeSinonimoController {

	private static final String SERVICE_PATH = "/EmbalajeSinonimos";

	@Autowired
	private EmbalajeSinonimoServices services;

	@GetMapping(path = SERVICE_PATH)
	public QueryResponse<EmbalajeSinonimo> findByEmbalaje(@RequestParam("emp") String empresa,
			@RequestParam("emb") String codEmb) {
		List<EmbalajeSinonimo> resultData = services.findByEmbalaje(empresa, codEmb);
		return new QueryResponse<>(resultData, resultData.size());
	}

	@PostMapping(path = SERVICE_PATH)
	public ProcessResponse create(@RequestBody EmbalajeSinonimo body) {
		services.create(body);
		return new ProcessResponse(true, "Insertado exitosamente.");
	}

	@PutMapping(path = SERVICE_PATH)
	public ProcessResponse update(@RequestBody EmbalajeSinonimo body) {
		if (services.update(body)) {
			return new ProcessResponse(true, "Editado exitosamente.");
		} else {
			return new ProcessResponse(false, "El registro no existe.");
		}
	}

	@DeleteMapping(path = SERVICE_PATH)
	public ProcessResponse delete(@RequestBody List<EmbalajeSinonimo> body) {
		services.delete(body);
		return new ProcessResponse(true, "Borrado exitosamente.");
	}
}
