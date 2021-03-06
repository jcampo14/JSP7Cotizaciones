package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aspsols.cotizaciones.excelViews.ClientesExcelView;
import com.aspsols.cotizaciones.model.Terceros;
import com.aspsols.cotizaciones.model.ViewClientes;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.TercerosServices;

@RestController
public class TercerosController {

	private static final String SERVICE_PATH = "/terceros";
	private static final String PATH_PROSPECTOS = "/prospectos";

	@Autowired
	private TercerosServices service;	

	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public QueryResponse<Terceros> findByEmpresa(@RequestParam("emp") String empresa,
			@RequestParam("filter") String filter) {
		QueryResponse<Terceros> response = new QueryResponse<>();
		List<Terceros> resultData = service.getTerceros(empresa, filter);
		response.setCount(resultData.size());
		response.setData(resultData);
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, path = PATH_PROSPECTOS)
	public QueryResponse<Terceros> findByProspectos(@RequestParam("emp") String empresa) {
		List<Terceros> resultData = service.getByProspecto(empresa);
		return new QueryResponse<>(resultData, resultData.size());
	}

	@RequestMapping(method = RequestMethod.GET, path = "/tercerosByNit")
	public QueryResponse<Terceros> findByNit(@RequestParam("emp") String empresa, @RequestParam("nit") String nit) {
		QueryResponse<Terceros> response = new QueryResponse<>();
		List<Terceros> resultData = service.getByNit(empresa, nit);
		response.setCount(resultData.size());
		response.setData(resultData);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, path = PATH_PROSPECTOS)
	public ProcessResponse insertProspecto(@RequestBody Terceros body) {
		ProcessResponse response = new ProcessResponse();
		service.insertProspecto(body);
		response.setSuccess(true);
		response.setMessage("Insertado correctamente.");
		return response;
	}

	@RequestMapping(method = RequestMethod.PUT, path = PATH_PROSPECTOS)
	public ProcessResponse updateProspecto(@RequestBody Terceros body) {
		service.updateProspecto(body);
		return new ProcessResponse(true, "Editado correctamente.");
	}

	@RequestMapping(method = RequestMethod.DELETE, path = PATH_PROSPECTOS)
	public ProcessResponse deleteProspecto(@RequestBody Terceros body) {
		service.deleteProspecto(body);
		return new ProcessResponse(true, "Borrado correctamente.");
	}
	
	@GetMapping(value = "/GetClientesDataBase")
	public ModelAndView getClientesDataBase(Model model, @RequestParam("empresa") String empresa) {
		List<ViewClientes> queryData = service.getClientesDataBase(empresa);
		return new ModelAndView(new ClientesExcelView(), "clientes", queryData);
	}
}
