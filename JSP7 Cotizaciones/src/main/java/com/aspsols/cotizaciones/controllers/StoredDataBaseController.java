package com.aspsols.cotizaciones.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.db.functions.Saldo;
import com.aspsols.cotizaciones.responses.SaldoInventarioResponse;

@RestController
public class StoredDataBaseController {

	@Autowired
	private Saldo saldo;

	@GetMapping(value = "/saldoInventario")
	public SaldoInventarioResponse getSaldoInventario(@RequestParam("empresa") String empresa,
			@RequestParam("agencia") String agencia, @RequestParam("bodega") String bodega,
			@RequestParam("codArticulo") String codArticulo, @RequestParam("fecha") Long fecha) {
		SaldoInventarioResponse response = new SaldoInventarioResponse();
		Date date = new Date(fecha);
		Double saldoInv = saldo.executeStored(empresa, agencia, bodega, codArticulo, date, null, null, null);
		response.setCodEmpresa(empresa);
		response.setCodAgencia(agencia);
		response.setCodBodega(bodega);
		response.setCodArticulo(codArticulo);
		response.setFecha(date);
		response.setSaldoInv(saldoInv);
		return response;
	}
}
