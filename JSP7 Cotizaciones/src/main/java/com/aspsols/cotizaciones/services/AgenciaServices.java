package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Agencia;
import com.aspsols.cotizaciones.repositories.AgenciaRepository;

@Service
public class AgenciaServices {

	@Autowired
	private AgenciaRepository repository;
	
	public List<Agencia> findByEmpresa(String empresa){
		return (List<Agencia>) repository.findByEmpresa(empresa);		
	}
}
