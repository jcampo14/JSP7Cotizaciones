package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.ArticuloDesCom;
import com.aspsols.cotizaciones.repositories.ArticuloDesComRepository;

@Service
public class ArticuloDesComServices {

	@Autowired
	private ArticuloDesComRepository repository;
	
	public List<ArticuloDesCom> findByArticuloAndIdioma(String empresa, String articulo, String idioma){
		return repository.findByArticuloAndIdioma(empresa, articulo, idioma);
	}
	
}
