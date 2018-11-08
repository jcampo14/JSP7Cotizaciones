package com.aspsols.cotizaciones.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.db.procedures.CreaCotizacion;
import com.aspsols.cotizaciones.db.procedures.CreaPedidoCotizacion;
import com.aspsols.cotizaciones.request.CotizacionAPedidoRequest;
import com.aspsols.cotizaciones.request.CotizacionRequest;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.services.CotizacionServices;

@RestController
public class CotizacionController {	

	@Autowired
	private CreaCotizacion creaCotizacion;

	@Autowired
	private CreaPedidoCotizacion creaPedidoCotizacion;

	@Autowired
	private CotizacionServices service;

	@RequestMapping(method = RequestMethod.POST, value = "/cotizacion")
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

	@RequestMapping(method = RequestMethod.POST, path = "/cotizacionAPedido")
	public ProcessResponse<CotizacionRequest> convertirCotizacionAPedido(
			@RequestBody CotizacionAPedidoRequest request) {
		Map<String, Object> resultData = creaPedidoCotizacion.execute(request.getcEmp(), request.getPer(),
				request.getcAgr(), request.getCot(), request.getRev());
		Integer codErr = (Integer) resultData.get("codError");
		String msgErr = (String) resultData.get("msgError");
		Integer numeroPedido = (Integer) resultData.get("numeroPed");
		if (codErr != 0) {
			return new ProcessResponse<>(false, msgErr);
		} else {
			return new ProcessResponse<>(true, msgErr + ".\n" + "Pedido No. " + numeroPedido + " generado.");
		}
	}

}
