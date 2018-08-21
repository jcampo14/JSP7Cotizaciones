package com.aspsols.cotizaciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.db.procedures.CreaCotizacion;
import com.aspsols.cotizaciones.request.CotizacionRequest;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.services.CotizacionServices;

@RestController
public class CotizacionController {
	
	private static final String SERVICE_PATH = "/cotizacion/";

	@Autowired
	private CreaCotizacion creaCotizacion;

	@Autowired
	private CotizacionServices service;

	@RequestMapping(method = RequestMethod.POST, value = SERVICE_PATH)
	public ProcessResponse<CotizacionRequest> crearCotizacion(@RequestBody CotizacionRequest request) {
		ProcessResponse<CotizacionRequest> response = new ProcessResponse<>();
		/* Generamos el ID de transaccion */
		String idTransaccion = java.util.UUID.randomUUID().toString();
		/* Insertamos en la tabla temporal */
		service.createCotizacion(request, idTransaccion);
		/* Ejecutamos el procedimiento almacenado */
		String resultData = creaCotizacion.execute(idTransaccion);		
		response.setSuccess(true);
		response.setMessage(resultData);			
		return response;
	};
}
