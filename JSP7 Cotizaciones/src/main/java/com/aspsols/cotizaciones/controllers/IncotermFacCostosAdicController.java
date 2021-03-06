package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.IncotermFacCostosAdic;
import com.aspsols.cotizaciones.model.lists.IncotermFacCostosAdicList;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.IncotermFacCostosAdicServices;

@RestController
public class IncotermFacCostosAdicController {

	private static final String SERVICE_PATH = "/incoterm-fac-costos-adic";

	@Autowired
	private IncotermFacCostosAdicServices service;

	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH)
	public QueryResponse<IncotermFacCostosAdic> obtener(@RequestParam("incoterm") String incoterm,
			@RequestParam("emp") String emp) {
		QueryResponse<IncotermFacCostosAdic> response = new QueryResponse<>();
		List<IncotermFacCostosAdic> list = service.findByIncoterm(incoterm, emp);
		response.setCount(list.size());
		response.setData(list);
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH + "ByMoneda")
	public QueryResponse<IncotermFacCostosAdic> findByIncotermAndMoneda(@RequestParam("incoterm") String incoterm,
			@RequestParam("moneda") String moneda, @RequestParam("emp") String empresa) {
		List<IncotermFacCostosAdic> list = service.findByIncotermAndMoneda(incoterm, moneda, empresa);
		return new QueryResponse<>(list, list.size());
	}

	@RequestMapping(method = RequestMethod.POST, value = SERVICE_PATH)
	public ProcessResponse insertar(@RequestBody IncotermFacCostosAdic body) {
		return service.insert(body);
	}

	@RequestMapping(method = RequestMethod.PUT, value = SERVICE_PATH)
	public ProcessResponse actualizar(@RequestBody IncotermFacCostosAdic body) {
		return service.update(body);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = SERVICE_PATH)
	public ProcessResponse eliminar(@RequestBody IncotermFacCostosAdicList body) {
		ProcessResponse response = new ProcessResponse();
		response.setSuccess(true);
		response.setMessage("OK");
		for (IncotermFacCostosAdic record : body.getList()) {
			ProcessResponse responseRecord = service.delete(record);
			if (!responseRecord.isSuccess()) {
				response.setSuccess(false);
				response.setMessage(responseRecord.getMessage());
			}
		}
		return response;
	}
}
