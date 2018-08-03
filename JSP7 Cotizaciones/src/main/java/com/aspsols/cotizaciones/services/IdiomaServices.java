package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Idioma;
import com.aspsols.cotizaciones.model.IdiomaId;
import com.aspsols.cotizaciones.repositories.IdiomaRepository;
import com.aspsols.cotizaciones.responses.PostResponse;

@Service
public class IdiomaServices {

	@Autowired
	private IdiomaRepository repository;
	
	public Idioma findById(IdiomaId id) {
		return repository.findOne(id);
	}

	public PostResponse<Idioma> insert(Idioma model) {
		PostResponse<Idioma> response = new PostResponse<Idioma>();
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

	public PostResponse<Idioma> update(Idioma model) {
		PostResponse<Idioma> response = new PostResponse<Idioma>();
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

	public PostResponse<Idioma> delete(Idioma model) {
		PostResponse<Idioma> response = new PostResponse<Idioma>();
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
