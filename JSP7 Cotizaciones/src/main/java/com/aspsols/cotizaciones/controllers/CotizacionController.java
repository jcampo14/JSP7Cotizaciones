package com.aspsols.cotizaciones.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.db.procedures.CreaCotizacion;
import com.aspsols.cotizaciones.request.CotizacionRequest;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.responses.QueryResponse;
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
		service.insertTemporalTable(request, idTransaccion);
		/* Ejecutamos el procedimiento almacenado */
		Map<String, Object> resultData = creaCotizacion.execute(idTransaccion);
		Integer codErr = (Integer) resultData.get("codError");
		String msgErr = (String) resultData.get("msgError");
		Integer numeroCot = (Integer) resultData.get("numeroCot");
		Integer numeroRev = (Integer) resultData.get("numeroRev");
		if (codErr != 0) {
			response.setSuccess(false);
			response.setMessage(msgErr);
		} else {
			response.setSuccess(true);
			response.setMessage(msgErr + ".\n" + "Cotizacion No. " + numeroCot + "\n" + " Revision No. " + numeroRev
					+ " generada.");
		}
		/* Borramos de la tabla temporal */
		service.deleteTemporalTable(idTransaccion);
		return response;
	};
		
}
