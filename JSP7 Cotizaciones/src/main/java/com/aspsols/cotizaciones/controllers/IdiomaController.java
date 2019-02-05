package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Idioma;
import com.aspsols.cotizaciones.model.lists.IdiomaList;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.IdiomaServices;

@RestController
public class IdiomaController {

	private static final String SERVICE_PATH = "/idiomas";

	@Autowired
	private IdiomaServices service;

	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH)
	public QueryResponse<Idioma> obtener(@RequestParam("emp") String codEmp) {
		QueryResponse<Idioma> response = new QueryResponse<>();
		List<Idioma> list = service.showByEmpresa(codEmp);
		response.setCount(list.size());
		response.setData(list);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = SERVICE_PATH)
	public ProcessResponse insertar(@RequestBody Idioma body) {
		return service.insert(body);
	}

	@RequestMapping(method = RequestMethod.PUT, value = SERVICE_PATH)
	public ProcessResponse actualizar(@RequestBody Idioma body) {
		return service.update(body);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = SERVICE_PATH)
	public ProcessResponse eliminar(@RequestBody IdiomaList body) {
		ProcessResponse response = new ProcessResponse();
		response.setSuccess(true);
		response.setMessage("OK");
		for (Idioma record : body.getList()) {
			ProcessResponse responseRecord = service.delete(record);
			if (!responseRecord.isSuccess()) {
				response.setSuccess(false);
				response.setMessage(responseRecord.getMessage());
			}
		}
		return response;
	}
}
