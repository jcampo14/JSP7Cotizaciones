package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.ArticuloDesCom;
import com.aspsols.cotizaciones.model.lists.ArticuloDesComList;
import com.aspsols.cotizaciones.repositories.ArticuloDesComRepository;

@Service
public class ArticuloDesComServices {

	@Autowired
	private ArticuloDesComRepository repository;

	public List<ArticuloDesCom> findByArticuloAndIdioma(String empresa, String articulo, String idioma) {
		return repository.findByArticuloAndIdioma(empresa, articulo, idioma);
	}

	public List<ArticuloDesCom> findByArticulo(String empresa, String articulo) {
		return repository.findByArticulo(empresa, articulo);
	}

	public void create(ArticuloDesCom entity) {
		repository.save(entity);
	}

	public boolean update(ArticuloDesCom entity) {
		ArticuloDesCom exist = repository.findOne(entity.getId());
		if (exist != null) {
			repository.save(entity);
			return true;
		} else {
			return false;
		}
	}
	
	public void delete(ArticuloDesComList entities) {
		repository.delete(entities.getList());
	}

}
