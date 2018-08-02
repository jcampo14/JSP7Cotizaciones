package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.IncotermFacCostosAdic;
import com.aspsols.cotizaciones.model.IncotermFacCostosAdicId;
import com.aspsols.cotizaciones.repositories.IncotermFacCostosAdicRepository;
import com.aspsols.cotizaciones.responses.PostResponse;

@Service
public class IncotermFacCostosAdicServices {
	
	@Autowired
	private IncotermFacCostosAdicRepository repository;
	
	public IncotermFacCostosAdic findById(IncotermFacCostosAdicId id) {
		return repository.findOne(id);
	}

	public PostResponse<IncotermFacCostosAdic> insert(IncotermFacCostosAdic model) {
		PostResponse<IncotermFacCostosAdic> response = new PostResponse<IncotermFacCostosAdic>();
		IncotermFacCostosAdicId id = new IncotermFacCostosAdicId();
		id.setcEmp(model.getcEmp());
		id.setIdFacCostosAdic(model.getIdFacCostosAdic());
		id.setIdIncoterm(model.getIdIncoterm());
		IncotermFacCostosAdic record = repository.findOne(id);
		if (record == null) {
			repository.save(model);
			response.setSuccess(true);
			response.setMessage("OK");
		} else {
			response.setSuccess(false);
			response.setMessage("Registro Duplicado");
		}
		return response;
	}

	public PostResponse<IncotermFacCostosAdic> update(IncotermFacCostosAdic model) {
		PostResponse<IncotermFacCostosAdic> response = new PostResponse<IncotermFacCostosAdic>();
		IncotermFacCostosAdicId id = new IncotermFacCostosAdicId();
		id.setcEmp(model.getcEmp());
		id.setIdFacCostosAdic(model.getIdFacCostosAdic());
		id.setIdIncoterm(model.getIdIncoterm());
		IncotermFacCostosAdic record = repository.findOne(id);
		if (record == null) {
			response.setSuccess(false);
			response.setMessage("No existe el registro");			
		} else {			
			repository.save(model);
			response.setSuccess(true);
			response.setMessage("OK");
		}
		return response;		
	}

	public PostResponse<IncotermFacCostosAdic> delete(IncotermFacCostosAdic model) {
		PostResponse<IncotermFacCostosAdic> response = new PostResponse<IncotermFacCostosAdic>();
		IncotermFacCostosAdicId id = new IncotermFacCostosAdicId();
		id.setcEmp(model.getcEmp());
		id.setIdFacCostosAdic(model.getIdFacCostosAdic());
		id.setIdIncoterm(model.getIdIncoterm());
		IncotermFacCostosAdic record = repository.findOne(id);
		if (record == null) {
			response.setSuccess(false);
			response.setMessage("No existe el registro");			
		} else {
			repository.delete(model);
			response.setSuccess(true);
			response.setMessage("OK");
		}
		return response;		
	}
	
	public List<IncotermFacCostosAdic> showAll() {
		return (List<IncotermFacCostosAdic>) repository.findAll();		
	}
	
	public List<IncotermFacCostosAdic> findByIncoterm(String incoterm, String emp){
		return (List<IncotermFacCostosAdic>) repository.findByIncoterm(incoterm, emp);
	}
		
}
