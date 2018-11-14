package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.EmbalajeSinonimo;
import com.aspsols.cotizaciones.model.ids.EmbalajeSinonimoId;
import com.aspsols.cotizaciones.repositories.EmbalajeSinonimoRepository;

@Service
public class EmbalajeSinonimoServices {

	@Autowired
	private EmbalajeSinonimoRepository repository;

	public List<EmbalajeSinonimo> findByEmbalaje(String empresa, String codEmb) {
		return repository.findByEmbalaje(empresa, codEmb);
	}

	public void create(EmbalajeSinonimo entity) {
		repository.save(entity);
	}

	public boolean update(EmbalajeSinonimo entity) {
		EmbalajeSinonimo exist = repository
				.findOne(new EmbalajeSinonimoId(entity.getcEmp(), entity.getCodEmb(), entity.getIdioma()));
		if (exist != null) {
			repository.save(entity);
			return true;
		} else {
			return false;
		}
	}

	public void delete(List<EmbalajeSinonimo> entities) {
		repository.delete(entities);
	}
}
