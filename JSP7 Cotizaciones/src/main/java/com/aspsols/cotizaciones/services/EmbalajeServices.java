package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Embalaje;
import com.aspsols.cotizaciones.model.ids.EmbalajeId;
import com.aspsols.cotizaciones.repositories.EmbalajeRepository;
import com.aspsols.cotizaciones.responses.ProcessResponse;

@Service
public class EmbalajeServices {

	@Autowired
	EmbalajeRepository repository;

	public List<Embalaje> showByEmpresa(String cEmp) {
		return (List<Embalaje>) repository.findByEmpresa(cEmp);
	}

	public ProcessResponse Insert(Embalaje model) {
		ProcessResponse response = new ProcessResponse();
		EmbalajeId Id = new EmbalajeId();
		Id.setcEmp(model.getcEmp());
		Id.setcEmb(model.getcEmb());
		Embalaje record = repository.findOne(Id);

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

	public ProcessResponse Update(Embalaje model) {
		ProcessResponse response = new ProcessResponse();
		EmbalajeId Id = new EmbalajeId();
		Id.setcEmp(model.getcEmp());
		Id.setcEmb(model.getcEmb());
		Embalaje record = repository.findOne(Id);

		if (record == null) {
			response.setSuccess(false);
			response.setMessage("El registro no existe");
		} else {
			repository.save(model);
			response.setSuccess(true);
			response.setMessage("OK");
		}

		return response;

	}

	public ProcessResponse Delete(Embalaje model) {
		ProcessResponse response = new ProcessResponse();
		EmbalajeId Id = new EmbalajeId();
		Id.setcEmp(model.getcEmp());
		Id.setcEmb(model.getcEmb());
		Embalaje record = repository.findOne(Id);

		if (record == null) {
			response.setSuccess(false);
			response.setMessage("El registro no existe");
		} else {
			repository.delete(model);
			response.setSuccess(true);
			response.setMessage("OK");
		}

		return response;

	}

}
