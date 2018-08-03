package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.CotSeccionesSinonimos;
import com.aspsols.cotizaciones.model.CotSeccionesSinonimosId;
import com.aspsols.cotizaciones.repositories.CotSeccionesSinonimosRepository;
import com.aspsols.cotizaciones.responses.PostResponse;

@Service
public class CotSeccionesSinonimosServices {

	@Autowired
	private CotSeccionesSinonimosRepository repository;
	
	public CotSeccionesSinonimos findById(CotSeccionesSinonimosId id) {
		return repository.findOne(id);
	}

	public PostResponse<CotSeccionesSinonimos> insert(CotSeccionesSinonimos model) {
		PostResponse<CotSeccionesSinonimos> response = new PostResponse<CotSeccionesSinonimos>();
		CotSeccionesSinonimosId id = new CotSeccionesSinonimosId();
		id.setcEmp(model.getcEmp());
		id.setCodSeccion(model.getCodSeccion());
		id.setIdioma(model.getIdioma());
		CotSeccionesSinonimos record = repository.findOne(id);
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

	public PostResponse<CotSeccionesSinonimos> update(CotSeccionesSinonimos model) {
		PostResponse<CotSeccionesSinonimos> response = new PostResponse<CotSeccionesSinonimos>();
		CotSeccionesSinonimosId id = new CotSeccionesSinonimosId();
		id.setcEmp(model.getcEmp());
		id.setCodSeccion(model.getCodSeccion());
		id.setIdioma(model.getIdioma());
		CotSeccionesSinonimos record = repository.findOne(id);
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

	public PostResponse<CotSeccionesSinonimos> delete(CotSeccionesSinonimos model) {
		PostResponse<CotSeccionesSinonimos> response = new PostResponse<CotSeccionesSinonimos>();
		CotSeccionesSinonimosId id = new CotSeccionesSinonimosId();
		id.setcEmp(model.getcEmp());
		id.setCodSeccion(model.getCodSeccion());
		id.setIdioma(model.getIdioma());		
		CotSeccionesSinonimos record = repository.findOne(id);
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

	public List<CotSeccionesSinonimos> showAll() {
		return (List<CotSeccionesSinonimos>) repository.findAll();
	}

	public List<CotSeccionesSinonimos> showBySeccion(String emp, String seccion) {
		return (List<CotSeccionesSinonimos>) repository.findBySeccion(emp, seccion);
	}
}
