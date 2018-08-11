package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Criterio;
import com.aspsols.cotizaciones.repositories.CriterioRepository;

@Service
public class CriterioServices {

	@Autowired
	private CriterioRepository repository;
	
	public List<Criterio> showByEmpresa(String empresa){		
		return (List<Criterio>) repository.findByEmpresa(empresa);
	};
	
}
