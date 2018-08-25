package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.CotSecciones;
import com.aspsols.cotizaciones.model.lists.CotSeccionesList;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.CotSeccionesServices;

@RestController
public class CotSeccionesController {
	
	private static final String SERVICE_PATH = "/cot-secciones/";
	
	@Autowired CotSeccionesServices service;
	
	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH)
	public QueryResponse<CotSecciones> obtener(@RequestParam("emp") String codEmp) {
		QueryResponse<CotSecciones> response = new QueryResponse<>();
		List<CotSecciones> list = service.showByEmpresa(codEmp);
		response.setCount(list.size());
		response.setData(list);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = SERVICE_PATH)
	public ProcessResponse<CotSecciones> insertar(@RequestBody CotSecciones body) {
		return service.insert(body);
	}

	@RequestMapping(method = RequestMethod.PUT, value = SERVICE_PATH)
	public ProcessResponse<CotSecciones> actualizar(@RequestBody CotSecciones body) {		
		return service.update(body);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = SERVICE_PATH)
	public ProcessResponse<CotSecciones> eliminar(@RequestBody CotSeccionesList body) {
		ProcessResponse<CotSecciones> response = new ProcessResponse<>();
		response.setSuccess(true);
		response.setMessage("OK");		
		for (CotSecciones record : body.getList()) {
			ProcessResponse<CotSecciones> responseRecord = service.delete(record);
			if(!responseRecord.isSuccess()) {
				response.setSuccess(false);
				response.setMessage(responseRecord.getMessage());
			}
		}
		return response;
	}
}
