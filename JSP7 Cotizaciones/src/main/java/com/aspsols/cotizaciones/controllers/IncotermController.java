package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Incoterm;
import com.aspsols.cotizaciones.model.lists.IncotermList;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.IncotermServices;

@RestController
public class IncotermController {

	private static final String SERVICE_PATH = "/incoterms";
	
	@Autowired
	private IncotermServices service;

	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH)
	public QueryResponse<Incoterm> obtener(@RequestParam("emp") String codEmp) {
		QueryResponse<Incoterm> response = new QueryResponse<>();
		List<Incoterm> list = service.showByEmpresa(codEmp);
		response.setCount(list.size());
		response.setData(list);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = SERVICE_PATH)
	public ProcessResponse<Incoterm> insertar(@RequestBody Incoterm body) {
		return service.insert(body);
	}

	@RequestMapping(method = RequestMethod.PUT, value = SERVICE_PATH)
	public ProcessResponse<Incoterm> actualizar(@RequestBody Incoterm body) {		
		return service.update(body);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = SERVICE_PATH)
	public ProcessResponse<Incoterm> eliminar(@RequestBody IncotermList body) {
		ProcessResponse<Incoterm> response = new ProcessResponse<>();
		response.setSuccess(true);
		response.setMessage("OK");		
		for (Incoterm record : body.getList()) {
			ProcessResponse<Incoterm> responseRecord = service.delete(record);
			if(!responseRecord.isSuccess()) {
				response.setSuccess(false);
				response.setMessage(responseRecord.getMessage());
			}
		}
		return response;
	}

}
