package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.FacCostosAdic;
import com.aspsols.cotizaciones.model.ids.FacCostosAdicId;
import com.aspsols.cotizaciones.repositories.FacCostosAdicRepository;
import com.aspsols.cotizaciones.responses.ProcessResponse;

@Service
public class FacCostosAdicServices {

	@Autowired
	FacCostosAdicRepository repository;

	public FacCostosAdic findById(FacCostosAdicId id) {
		return repository.findOne(id);
	}

	public ProcessResponse<FacCostosAdic> insert(FacCostosAdic model) {
		ProcessResponse<FacCostosAdic> response = new ProcessResponse<FacCostosAdic>();
		FacCostosAdicId id = new FacCostosAdicId();
		id.setcEmp(model.getcEmp());
		id.setCodigo(model.getCodigo());
		FacCostosAdic record = repository.findOne(id);
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

	public ProcessResponse<FacCostosAdic> update(FacCostosAdic model) {
		ProcessResponse<FacCostosAdic> response = new ProcessResponse<FacCostosAdic>();
		FacCostosAdicId id = new FacCostosAdicId();
		id.setcEmp(model.getcEmp());
		id.setCodigo(model.getCodigo());
		FacCostosAdic record = repository.findOne(id);
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

	public ProcessResponse<FacCostosAdic> delete(FacCostosAdic model) {
		ProcessResponse<FacCostosAdic> response = new ProcessResponse<FacCostosAdic>();
		FacCostosAdicId id = new FacCostosAdicId();
		id.setcEmp(model.getcEmp());
		id.setCodigo(model.getCodigo());
		FacCostosAdic record = repository.findOne(id);
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

	public List<FacCostosAdic> showAll() {
		return (List<FacCostosAdic>) repository.findAll();
	}

	public List<FacCostosAdic> showByEmpresa(String emp) {
		return (List<FacCostosAdic>) repository.findByEmpresa(emp);
	}

}
