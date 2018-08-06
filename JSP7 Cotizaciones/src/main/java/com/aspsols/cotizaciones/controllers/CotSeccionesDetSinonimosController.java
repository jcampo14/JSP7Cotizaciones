package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.CotSeccionesDetSinonimos;
import com.aspsols.cotizaciones.model.CotSeccionesDetSinonimosList;
import com.aspsols.cotizaciones.responses.GetResponse;
import com.aspsols.cotizaciones.responses.PostResponse;
import com.aspsols.cotizaciones.services.CotSeccionesDetSinonimosServices;

@RestController
public class CotSeccionesDetSinonimosController {

	private static final String SERVICE_PATH = "/cot-secciones-det-sinonimos/";

	@Autowired
	private CotSeccionesDetSinonimosServices service;

	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH)
	public GetResponse<CotSeccionesDetSinonimos> obtener(@RequestParam("seccion") String codSeccion,
			@RequestParam("emp") String cEmp, @RequestParam("codVal") String codVal) {
		GetResponse<CotSeccionesDetSinonimos> response = new GetResponse<>();
		List<CotSeccionesDetSinonimos> list = service.showBySeccionDetalle(codSeccion, codVal, cEmp);
		response.setCount(list.size());
		response.setData(list);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = SERVICE_PATH)
	public PostResponse<CotSeccionesDetSinonimos> insertar(@RequestBody CotSeccionesDetSinonimos body) {
		return service.insert(body);
	}

	@RequestMapping(method = RequestMethod.PUT, value = SERVICE_PATH)
	public PostResponse<CotSeccionesDetSinonimos> actualizar(@RequestBody CotSeccionesDetSinonimos body) {
		return service.update(body);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = SERVICE_PATH)
	public PostResponse<CotSeccionesDetSinonimos> eliminar(@RequestBody CotSeccionesDetSinonimosList body) {
		PostResponse<CotSeccionesDetSinonimos> response = new PostResponse<>();
		response.setSuccess(true);
		response.setMessage("OK");
		for (CotSeccionesDetSinonimos record : body.getList()) {
			PostResponse<CotSeccionesDetSinonimos> responseRecord = service.delete(record);
			if (!responseRecord.isSuccess()) {
				response.setSuccess(false);
				response.setMessage(responseRecord.getMessage());
			}
		}
		return response;
	}

}
