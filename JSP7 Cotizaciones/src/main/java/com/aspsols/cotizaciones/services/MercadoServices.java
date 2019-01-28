package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Mercado;
import com.aspsols.cotizaciones.repositories.MercadoRepository;

@Service
public class MercadoServices {
	
	@Autowired
	private MercadoRepository repository;
	
	public List<Mercado> findByEmpresa(String empresa) {
		return repository.findByEmpresa(empresa);
	}

}
