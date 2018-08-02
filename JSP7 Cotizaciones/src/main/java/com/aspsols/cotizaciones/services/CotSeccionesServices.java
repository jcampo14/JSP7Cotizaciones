package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.CotSecciones;
import com.aspsols.cotizaciones.model.CotSeccionesId;
import com.aspsols.cotizaciones.repositories.CotSeccionesRepository;
import com.aspsols.cotizaciones.responses.PostResponse;

@Service
public class CotSeccionesServices {

	@Autowired
	private CotSeccionesRepository repository;
	
	public CotSecciones findById(CotSeccionesId id) {
		return repository.findOne(id);
	}

	public PostResponse<CotSecciones> insert(CotSecciones model) {
		PostResponse<CotSecciones> response = new PostResponse<CotSecciones>();
		CotSeccionesId id = new CotSeccionesId();
		id.setcEmp(model.getcEmp());
		id.setCodSeccion(model.getCodSeccion());
		CotSecciones record = repository.findOne(id);
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

	public PostResponse<CotSecciones> update(CotSecciones model) {
		PostResponse<CotSecciones> response = new PostResponse<CotSecciones>();
		CotSeccionesId id = new CotSeccionesId();
		id.setcEmp(model.getcEmp());
		id.setCodSeccion(model.getCodSeccion());
		CotSecciones record = repository.findOne(id);
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

	public PostResponse<CotSecciones> delete(CotSecciones model) {
		PostResponse<CotSecciones> response = new PostResponse<CotSecciones>();
		CotSeccionesId id = new CotSeccionesId();
		id.setcEmp(model.getcEmp());
		id.setCodSeccion(model.getCodSeccion());
		CotSecciones record = repository.findOne(id);
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

	public List<CotSecciones> showAll() {
		return (List<CotSecciones>) repository.findAll();
	}

	public List<CotSecciones> showByEmpresa(String emp) {
		return (List<CotSecciones>) repository.findByEmpresa(emp);
	}
}
