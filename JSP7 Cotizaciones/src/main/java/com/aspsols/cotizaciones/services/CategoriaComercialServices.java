package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.CategoriaComercial;
import com.aspsols.cotizaciones.model.ids.CategoriaComercialId;
import com.aspsols.cotizaciones.repositories.CategoriaComercialRepository;

@Service
public class CategoriaComercialServices {

	@Autowired
	private CategoriaComercialRepository repository;

	public List<CategoriaComercial> findByRoot(String empresa) {
		return repository.findByRoot(empresa);
	}

	public void create(CategoriaComercial entity) {
		repository.save(entity);
	}

	public boolean update(CategoriaComercial entity) {
		CategoriaComercial exist = repository.findOne(new CategoriaComercialId(entity.getcEmp(), entity.getCodCat()));
		if (exist != null) {
			repository.save(entity);
			return true;
		} else {
			return false;
		}
	}

	public void delete(CategoriaComercial entity) {
		repository.delete(entity);
	}
	
}
