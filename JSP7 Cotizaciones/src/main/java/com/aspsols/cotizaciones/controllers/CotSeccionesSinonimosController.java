package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.CotSeccionesSinonimos;
import com.aspsols.cotizaciones.model.CotSeccionesSinonimosList;
import com.aspsols.cotizaciones.responses.GetResponse;
import com.aspsols.cotizaciones.responses.PostResponse;
import com.aspsols.cotizaciones.services.CotSeccionesSinonimosServices;

@RestController
public class CotSeccionesSinonimosController {
	
	private static final String SERVICE_PATH = "/cot-secciones-sinonimos/";

	@Autowired
	private CotSeccionesSinonimosServices service;
	
	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH)
	public GetResponse<CotSeccionesSinonimos> obtener(@RequestParam("emp") String codEmp,@RequestParam("seccion") String seccion) {
		GetResponse<CotSeccionesSinonimos> response = new GetResponse<>();
		List<CotSeccionesSinonimos> list = service.showBySeccion(codEmp, seccion);
		response.setCount(list.size());
		response.setData(list);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = SERVICE_PATH)
	public PostResponse<CotSeccionesSinonimos> insertar(@RequestBody CotSeccionesSinonimos body) {
		return service.insert(body);
	}

	@RequestMapping(method = RequestMethod.PUT, value = SERVICE_PATH)
	public PostResponse<CotSeccionesSinonimos> actualizar(@RequestBody CotSeccionesSinonimos body) {		
		return service.update(body);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = SERVICE_PATH)
	public PostResponse<CotSeccionesSinonimos> eliminar(@RequestBody CotSeccionesSinonimosList body) {
		PostResponse<CotSeccionesSinonimos> response = new PostResponse<>();
		response.setSuccess(true);
		response.setMessage("OK");		
		for (CotSeccionesSinonimos record : body.getList()) {
			PostResponse<CotSeccionesSinonimos> responseRecord = service.delete(record);
			if(!responseRecord.isSuccess()) {
				response.setSuccess(false);
				response.setMessage(responseRecord.getMessage());
			}
		}
		return response;
	}
	
}
