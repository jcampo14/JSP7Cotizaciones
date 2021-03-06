package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.FacCostosAdic;
import com.aspsols.cotizaciones.model.lists.FacCostosAdicList;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.FacCostosAdicServices;

@RestController
public class FacCostosAdicController {

	private static final String SERVICE_PATH = "/fac-costos-adic";

	@Autowired
	FacCostosAdicServices service;

	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH)
	public QueryResponse<FacCostosAdic> obtener(@RequestParam("emp") String codEmp) {
		QueryResponse<FacCostosAdic> response = new QueryResponse<>();
		List<FacCostosAdic> list = service.showByEmpresa(codEmp);
		response.setCount(list.size());
		response.setData(list);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = SERVICE_PATH)
	public ProcessResponse insertar(@RequestBody FacCostosAdic body) {
		return service.insert(body);
	}

	@RequestMapping(method = RequestMethod.PUT, value = SERVICE_PATH)
	public ProcessResponse actualizar(@RequestBody FacCostosAdic body) {
		return service.update(body);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = SERVICE_PATH)
	public ProcessResponse eliminar(@RequestBody FacCostosAdicList body) {
		ProcessResponse response = new ProcessResponse();
		response.setSuccess(true);
		response.setMessage("OK");
		for (FacCostosAdic record : body.getList()) {
			ProcessResponse responseRecord = service.delete(record);
			if (!responseRecord.isSuccess()) {
				response.setSuccess(false);
				response.setMessage(responseRecord.getMessage());
			}
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH + "ByMoneda")
	public QueryResponse<FacCostosAdic> findByMoneda(@RequestParam("emp") String empresa,
			@RequestParam("mon") String moneda) {
		List<FacCostosAdic> resultData = service.findByMoneda(empresa, moneda);
		return new QueryResponse<>(resultData, resultData.size());
	}

}
