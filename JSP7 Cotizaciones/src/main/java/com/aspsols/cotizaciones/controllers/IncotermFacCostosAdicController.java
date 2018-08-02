package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.IncotermFacCostosAdic;
import com.aspsols.cotizaciones.model.IncotermFacCostosAdicList;
import com.aspsols.cotizaciones.responses.GetResponse;
import com.aspsols.cotizaciones.responses.PostResponse;
import com.aspsols.cotizaciones.services.IncotermFacCostosAdicServices;

@RestController
public class IncotermFacCostosAdicController {

	private static final String SERVICE_PATH = "/incoterm-fac-costos-adic/";

	@Autowired
	private IncotermFacCostosAdicServices service;

	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH)
	public GetResponse<IncotermFacCostosAdic> obtener(@RequestParam("incoterm") String incoterm,
			@RequestParam("emp") String emp) {
		GetResponse<IncotermFacCostosAdic> response = new GetResponse<>();
		List<IncotermFacCostosAdic> list = service.findByIncoterm(incoterm, emp);		
		response.setCount(list.size());
		response.setData(list);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = SERVICE_PATH)
	public PostResponse<IncotermFacCostosAdic> insertar(@RequestBody IncotermFacCostosAdic body) {
		return service.insert(body);
	}

	@RequestMapping(method = RequestMethod.PUT, value = SERVICE_PATH)
	public PostResponse<IncotermFacCostosAdic> actualizar(@RequestBody IncotermFacCostosAdic body) {
		return service.update(body);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = SERVICE_PATH)
	public PostResponse<IncotermFacCostosAdic> eliminar(@RequestBody IncotermFacCostosAdicList body) {
		PostResponse<IncotermFacCostosAdic> response = new PostResponse<>();
		response.setSuccess(true);
		response.setMessage("OK");
		for (IncotermFacCostosAdic record : body.getList()) {
			PostResponse<IncotermFacCostosAdic> responseRecord = service.delete(record);
			if (!responseRecord.isSuccess()) {
				response.setSuccess(false);
				response.setMessage(responseRecord.getMessage());
			}
		}
		return response;
	}
}
