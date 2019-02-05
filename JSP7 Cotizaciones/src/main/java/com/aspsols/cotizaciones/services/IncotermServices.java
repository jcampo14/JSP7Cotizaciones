package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Incoterm;
import com.aspsols.cotizaciones.model.ids.IncotermId;
import com.aspsols.cotizaciones.repositories.IncotermRepository;
import com.aspsols.cotizaciones.responses.ProcessResponse;

@Service
public class IncotermServices {

	@Autowired
	IncotermRepository repository;

	public Incoterm findById(IncotermId id) {
		return repository.findOne(id);
	}

	public ProcessResponse insert(Incoterm model) {
		ProcessResponse response = new ProcessResponse();
		IncotermId id = new IncotermId();
		id.setcEmp(model.getcEmp());
		id.setCodIncoterm(model.getCodIncoterm());
		Incoterm record = repository.findOne(id);
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

	public ProcessResponse update(Incoterm model) {
		ProcessResponse response = new ProcessResponse();
		IncotermId id = new IncotermId();
		id.setcEmp(model.getcEmp());
		id.setCodIncoterm(model.getCodIncoterm());
		Incoterm record = repository.findOne(id);
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

	public ProcessResponse delete(Incoterm model) {
		ProcessResponse response = new ProcessResponse();
		IncotermId id = new IncotermId();
		id.setcEmp(model.getcEmp());
		id.setCodIncoterm(model.getCodIncoterm());
		Incoterm record = repository.findOne(id);
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

	public List<Incoterm> showAll() {
		return (List<Incoterm>) repository.findAll();
	}

	public List<Incoterm> showByEmpresa(String emp) {
		return (List<Incoterm>) repository.findByEmpresa(emp);
	}
}
