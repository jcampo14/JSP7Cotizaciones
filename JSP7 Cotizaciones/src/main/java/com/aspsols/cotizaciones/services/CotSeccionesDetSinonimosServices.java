package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.CotSeccionesDetSinonimos;
import com.aspsols.cotizaciones.model.CotSeccionesDetSinonimosId;
import com.aspsols.cotizaciones.repositories.CotSeccionesDetSinonimosRepository;
import com.aspsols.cotizaciones.responses.PostResponse;

@Service
public class CotSeccionesDetSinonimosServices {

	@Autowired
	private CotSeccionesDetSinonimosRepository repository;

	public CotSeccionesDetSinonimos findById(CotSeccionesDetSinonimosId id) {
		return repository.findOne(id);
	}

	public PostResponse<CotSeccionesDetSinonimos> insert(CotSeccionesDetSinonimos model) {
		PostResponse<CotSeccionesDetSinonimos> response = new PostResponse<CotSeccionesDetSinonimos>();
		CotSeccionesDetSinonimosId id = new CotSeccionesDetSinonimosId(model.getcEmp(), model.getCodSeccion(),
				model.getCodVal(), model.getIdioma());
		CotSeccionesDetSinonimos record = repository.findOne(id);
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

	public PostResponse<CotSeccionesDetSinonimos> update(CotSeccionesDetSinonimos model) {
		PostResponse<CotSeccionesDetSinonimos> response = new PostResponse<CotSeccionesDetSinonimos>();
		CotSeccionesDetSinonimosId id = new CotSeccionesDetSinonimosId(model.getcEmp(), model.getCodSeccion(),
				model.getCodVal(), model.getIdioma());
		CotSeccionesDetSinonimos record = repository.findOne(id);
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

	public PostResponse<CotSeccionesDetSinonimos> delete(CotSeccionesDetSinonimos model) {
		PostResponse<CotSeccionesDetSinonimos> response = new PostResponse<CotSeccionesDetSinonimos>();
		CotSeccionesDetSinonimosId id = new CotSeccionesDetSinonimosId(model.getcEmp(), model.getCodSeccion(),
				model.getCodVal(), model.getIdioma());
		CotSeccionesDetSinonimos record = repository.findOne(id);
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

	public List<CotSeccionesDetSinonimos> showAll() {
		return (List<CotSeccionesDetSinonimos>) repository.findAll();
	}

	public List<CotSeccionesDetSinonimos> showBySeccionDetalle(String codSeccion, String codVal, String cEmp) {
		return (List<CotSeccionesDetSinonimos>) repository.findBySeccionDetalle(codSeccion, codVal, cEmp);
	}

}
