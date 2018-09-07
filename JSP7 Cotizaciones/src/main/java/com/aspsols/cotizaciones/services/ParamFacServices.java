package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.ParamFac;
import com.aspsols.cotizaciones.repositories.ParamFacRepository;

@Service
public class ParamFacServices {

	@Autowired
	private ParamFacRepository repository;
	
	public List<ParamFac> findByEmpresa(String empresa){
		return repository.findByEmpresa(empresa);		
	}
}
