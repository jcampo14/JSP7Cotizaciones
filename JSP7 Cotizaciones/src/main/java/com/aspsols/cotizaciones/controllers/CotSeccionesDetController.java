package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.CotSeccionesDet;
import com.aspsols.cotizaciones.model.lists.CotSeccionesDetList;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.CotSeccionesDetServices;

@RestController
public class CotSeccionesDetController {

	private static final String SERVICE_PATH = "/cot-secciones-det";

	@Autowired
	private CotSeccionesDetServices service;

	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH)
	public QueryResponse<CotSeccionesDet> obtener(@RequestParam("seccion") String seccion,
			@RequestParam("emp") String emp) {
		QueryResponse<CotSeccionesDet> response = new QueryResponse<>();
		List<CotSeccionesDet> list = service.showBySeccion(seccion, emp);
		response.setCount(list.size());
		response.setData(list);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = SERVICE_PATH)
	public ProcessResponse<CotSeccionesDet> insertar(@RequestBody CotSeccionesDet body) {
		return service.insert(body);
	}

	@RequestMapping(method = RequestMethod.PUT, value = SERVICE_PATH)
	public ProcessResponse<CotSeccionesDet> actualizar(@RequestBody CotSeccionesDet body) {
		return service.update(body);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = SERVICE_PATH)
	public ProcessResponse<CotSeccionesDet> eliminar(@RequestBody CotSeccionesDetList body) {
		ProcessResponse<CotSeccionesDet> response = new ProcessResponse<>();
		response.setSuccess(true);
		response.setMessage("OK");
		for (CotSeccionesDet record : body.getList()) {
			ProcessResponse<CotSeccionesDet> responseRecord = service.delete(record);
			if (!responseRecord.isSuccess()) {
				response.setSuccess(false);
				response.setMessage(responseRecord.getMessage());
			}
		}
		return response;
	}

}
