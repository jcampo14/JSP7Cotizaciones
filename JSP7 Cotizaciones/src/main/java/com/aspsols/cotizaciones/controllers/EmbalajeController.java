package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Embalaje;
import com.aspsols.cotizaciones.model.Incoterm;
import com.aspsols.cotizaciones.model.lists.EmbalajeList;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.responses.QueryResponse;
import com.aspsols.cotizaciones.services.EmbalajeServices;

@RestController
public class EmbalajeController {

	private static final String SERVICE_PATH = "/embalajes";
	
	@Autowired
	private EmbalajeServices services;
	
	@RequestMapping(method=RequestMethod.GET, path=SERVICE_PATH)
	public QueryResponse<Embalaje> showByEmpresaServices(@RequestParam("emp") String emp){
		QueryResponse<Embalaje> response = new QueryResponse<>();
		List<Embalaje> list = services.showByEmpresa(emp);
		response.setCount(list.size());
		response.setData(list);
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.POST, path=SERVICE_PATH)
	public ProcessResponse<Embalaje> Insert(@RequestBody Embalaje body){
		return services.Insert(body);
	}
	
	@RequestMapping(method=RequestMethod.PUT, path=SERVICE_PATH)
	public ProcessResponse<Embalaje> Update(@RequestBody Embalaje body){
		return services.Update(body);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path=SERVICE_PATH)
	public ProcessResponse<Embalaje> Delete(@RequestBody EmbalajeList body){
		ProcessResponse<Embalaje> response = new ProcessResponse<Embalaje>();
		response.setSuccess(true);
		response.setMessage("OK");	
		
		for(Embalaje record : body.getList()) {
			ProcessResponse<Embalaje> responseRecord = services.Delete(record);
			if(!responseRecord.isSuccess()) {
				response.setSuccess(false);
				response.setMessage(responseRecord.getMessage());
			}
		}
		
		return response;
	}
	
}
