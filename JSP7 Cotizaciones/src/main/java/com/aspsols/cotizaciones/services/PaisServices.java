package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Pais;
import com.aspsols.cotizaciones.repositories.PaisRepository;

@Service
public class PaisServices {

	@Autowired
	private PaisRepository repository;
	
	public List<Pais> findByEmpresa(String empresa) {
		return repository.findByEmpresa(empresa);
	}
}
