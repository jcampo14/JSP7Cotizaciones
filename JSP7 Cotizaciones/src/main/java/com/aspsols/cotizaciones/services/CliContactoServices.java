package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.CliContacto;
import com.aspsols.cotizaciones.repositories.CliContactoRepository;

@Service
public class CliContactoServices {

	@Autowired
	private CliContactoRepository repository;

	public List<CliContacto> findByNit(String empresa, String nit) {
		return repository.findByNit(empresa, nit);
	}

	public void create(CliContacto entity) {
		repository.save(entity);
	}

	public boolean update(CliContacto entity) {
		CliContacto exist = repository.findOne(entity.getId());
		if (exist != null) {
			repository.save(entity);
			return true;
		} else {
			return false;
		}
	}
	
	public void delete(List<CliContacto> entities) {
		repository.delete(entities);		
	}

}
