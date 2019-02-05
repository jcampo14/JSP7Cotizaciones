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

import com.aspsols.cotizaciones.model.CliContacto;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.CliContactoServices;

@RestController
public class CliContactoController {

	private static final String SERVICE_PATH = "/cli-contacto";

	@Autowired
	private CliContactoServices services;

	@GetMapping(path = SERVICE_PATH)
	public QueryResponse<CliContacto> findByNit(@RequestParam("emp") String empresa, @RequestParam("nit") String nit) {
		List<CliContacto> resultData = services.findByNit(empresa, nit);
		return new QueryResponse<>(resultData, resultData.size());
	}

	@PostMapping(path = SERVICE_PATH)
	public ProcessResponse create(@RequestBody CliContacto body) {
		services.create(body);
		return new ProcessResponse(true, "Insertado exitosamente.");
	}

	@PutMapping(path = SERVICE_PATH)
	public ProcessResponse update(@RequestBody CliContacto body) {
		services.update(body);
		return new ProcessResponse(true, "Editado exitosamente.");
	}

	@DeleteMapping(path = SERVICE_PATH)
	public ProcessResponse delete(@RequestBody List<CliContacto> body) {
		services.delete(body);
		return new ProcessResponse(true, "Borrado exitosamente");
	}

}
