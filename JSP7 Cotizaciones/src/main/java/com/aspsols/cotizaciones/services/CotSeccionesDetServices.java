package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.CotSeccionesDet;
import com.aspsols.cotizaciones.model.ids.CotSeccionesDetId;
import com.aspsols.cotizaciones.repositories.CotSeccionesDetRepository;
import com.aspsols.cotizaciones.responses.ProcessResponse;

@Service
public class CotSeccionesDetServices {

	@Autowired
	private CotSeccionesDetRepository repository;

	public CotSeccionesDet findById(CotSeccionesDetId id) {
		return repository.findOne(id);
	}

	public ProcessResponse<CotSeccionesDet> insert(CotSeccionesDet model) {
		ProcessResponse<CotSeccionesDet> response = new ProcessResponse<CotSeccionesDet>();
		CotSeccionesDetId id = new CotSeccionesDetId(model.getcEmp(), model.getCodSeccion(), model.getCodVal());
		CotSeccionesDet record = repository.findOne(id);
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

	public ProcessResponse<CotSeccionesDet> update(CotSeccionesDet model) {
		ProcessResponse<CotSeccionesDet> response = new ProcessResponse<CotSeccionesDet>();
		CotSeccionesDetId id = new CotSeccionesDetId(model.getcEmp(), model.getCodSeccion(), model.getCodVal());
		CotSeccionesDet record = repository.findOne(id);
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

	public ProcessResponse<CotSeccionesDet> delete(CotSeccionesDet model) {
		ProcessResponse<CotSeccionesDet> response = new ProcessResponse<CotSeccionesDet>();
		CotSeccionesDetId id = new CotSeccionesDetId(model.getcEmp(), model.getCodSeccion(), model.getCodVal());
		CotSeccionesDet record = repository.findOne(id);
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

	public List<CotSeccionesDet> showAll() {
		return (List<CotSeccionesDet>) repository.findAll();
	}

	public List<CotSeccionesDet> showBySeccion(String seccion, String emp) {
		return (List<CotSeccionesDet>) repository.findBySeccion(seccion, emp);
	}

}
