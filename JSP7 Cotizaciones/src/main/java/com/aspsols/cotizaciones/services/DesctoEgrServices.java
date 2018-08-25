package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.DesctoEgr;
import com.aspsols.cotizaciones.repositories.DesctoEgrRepository;

@Service
public class DesctoEgrServices {

	@Autowired
	private DesctoEgrRepository repository;
	
	public List<DesctoEgr> findIvasByEmpresa(String empresa){
		return (List<DesctoEgr>) repository.findIvasByEmpresa(empresa);		
	}
}
