package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Idioma;
import com.aspsols.cotizaciones.model.IdiomaList;
import com.aspsols.cotizaciones.responses.GetResponse;
import com.aspsols.cotizaciones.responses.PostResponse;
import com.aspsols.cotizaciones.services.IdiomaServices;

@RestController
public class IdiomaController {

	private static final String SERVICE_PATH = "/idiomas/";

	@Autowired
	private IdiomaServices service;

	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH)
	public GetResponse<Idioma> obtener(@RequestParam("emp") String codEmp) {
		GetResponse<Idioma> response = new GetResponse<>();
		List<Idioma> list = service.showByEmpresa(codEmp);
		response.setCount(list.size());
		response.setData(list);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = SERVICE_PATH)
	public PostResponse<Idioma> insertar(@RequestBody Idioma body) {
		return service.insert(body);
	}

	@RequestMapping(method = RequestMethod.PUT, value = SERVICE_PATH)
	public PostResponse<Idioma> actualizar(@RequestBody Idioma body) {
		return service.update(body);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = SERVICE_PATH)
	public PostResponse<Idioma> eliminar(@RequestBody IdiomaList body) {
		PostResponse<Idioma> response = new PostResponse<>();
		response.setSuccess(true);
		response.setMessage("OK");
		for (Idioma record : body.getList()) {
			PostResponse<Idioma> responseRecord = service.delete(record);
			if (!responseRecord.isSuccess()) {
				response.setSuccess(false);
				response.setMessage(responseRecord.getMessage());
			}
		}
		return response;
	}
}
