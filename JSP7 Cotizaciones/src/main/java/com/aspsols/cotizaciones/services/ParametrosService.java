package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Parametros;
import com.aspsols.cotizaciones.repositories.ParametrosRepository;

@Service
public class ParametrosService {

	@Autowired
	private ParametrosRepository repository;
	
	public List<Parametros> findAll(){
		return (List<Parametros>) repository.findAll();
	}
}
