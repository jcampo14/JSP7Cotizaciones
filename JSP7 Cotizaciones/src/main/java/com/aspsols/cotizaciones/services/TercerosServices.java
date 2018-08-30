package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Terceros;
import com.aspsols.cotizaciones.repositories.TercerosRepository;

@Service
public class TercerosServices {

	@Autowired
	private TercerosRepository repository;
	
	public List<Terceros> getTerceros(String empresa, String filter){
		return repository.findByEmpresa(empresa, filter);
	}
	
}
