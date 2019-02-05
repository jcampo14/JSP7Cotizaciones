package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Idioma;
import com.aspsols.cotizaciones.model.ids.IdiomaId;
import com.aspsols.cotizaciones.repositories.IdiomaRepository;
import com.aspsols.cotizaciones.responses.ProcessResponse;

@Service
public class IdiomaServices {

	@Autowired
	private IdiomaRepository repository;

	public Idioma findById(IdiomaId id) {
		return repository.findOne(id);
	}

	public ProcessResponse insert(Idioma model) {
		ProcessResponse response = new ProcessResponse();
		IdiomaId id = new IdiomaId();
		id.setcEmp(model.getcEmp());
		id.setIdioma(model.getIdioma());
		Idioma record = repository.findOne(id);
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

	public ProcessResponse update(Idioma model) {
		ProcessResponse response = new ProcessResponse();
		IdiomaId id = new IdiomaId();
		id.setcEmp(model.getcEmp());
		id.setIdioma(model.getIdioma());
		Idioma record = repository.findOne(id);
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

	public ProcessResponse delete(Idioma model) {
		ProcessResponse response = new ProcessResponse();
		IdiomaId id = new IdiomaId();
		id.setcEmp(model.getcEmp());
		id.setIdioma(model.getIdioma());
		Idioma record = repository.findOne(id);
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

	public List<Idioma> showAll() {
		return (List<Idioma>) repository.findAll();
	}

	public List<Idioma> showByEmpresa(String emp) {
		return (List<Idioma>) repository.findByEmpresa(emp);
	}

}
