package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Zona;
import com.aspsols.cotizaciones.repositories.ZonaRepository;

@Service
public class ZonaServices {

	@Autowired
	private ZonaRepository repository;
	
	public List<Zona> findByEmpresa(String empresa){
		return repository.findByEmpresa(empresa);
	}
}
