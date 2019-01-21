package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Vendedor;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.VendedorServices;

@RestController
public class VendedorController {

	private static final String SERVICE_PATH = "/vendedor";

	@Autowired
	private VendedorServices services;

	@GetMapping(value = SERVICE_PATH)
	public QueryResponse<Vendedor> findByEmpresa(@RequestParam("emp") String empresa) {
		List<Vendedor> data = services.findAll(empresa);
		return new QueryResponse<>(data, data.size());
	}

	@GetMapping(value = "/vendedorByNit")
	public Vendedor findByNit(@RequestParam("emp") String empresa, @RequestParam("nit") String nIde) {
		return services.findByNit(nIde, empresa);
	}

	@GetMapping(value = "/vendedorByUsuario")
	public Vendedor findByUsuario(@RequestParam("emp") String empresa, @RequestParam("usuario") String usuario) {
		return services.findByUsuario(usuario, empresa);
	}
}
