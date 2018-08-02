package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.CotSecciones;
import com.aspsols.cotizaciones.model.CotSeccionesList;
import com.aspsols.cotizaciones.responses.GetResponse;
import com.aspsols.cotizaciones.responses.PostResponse;
import com.aspsols.cotizaciones.services.CotSeccionesServices;

@RestController
public class CotSeccionesController {
	
	private static final String SERVICE_PATH = "/cot-secciones/";
	
	@Autowired CotSeccionesServices service;
	
	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH)
	public GetResponse<CotSecciones> obtener(@RequestParam("emp") String codEmp) {
		GetResponse<CotSecciones> response = new GetResponse<>();
		List<CotSecciones> list = service.showByEmpresa(codEmp);
		response.setCount(list.size());
		response.setData(list);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = SERVICE_PATH)
	public PostResponse<CotSecciones> insertar(@RequestBody CotSecciones body) {
		return service.insert(body);
	}

	@RequestMapping(method = RequestMethod.PUT, value = SERVICE_PATH)
	public PostResponse<CotSecciones> actualizar(@RequestBody CotSecciones body) {		
		return service.update(body);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = SERVICE_PATH)
	public PostResponse<CotSecciones> eliminar(@RequestBody CotSeccionesList body) {
		PostResponse<CotSecciones> response = new PostResponse<>();
		response.setSuccess(true);
		response.setMessage("OK");		
		for (CotSecciones record : body.getList()) {
			PostResponse<CotSecciones> responseRecord = service.delete(record);
			if(!responseRecord.isSuccess()) {
				response.setSuccess(false);
				response.setMessage(responseRecord.getMessage());
			}
		}
		return response;
	}
}
