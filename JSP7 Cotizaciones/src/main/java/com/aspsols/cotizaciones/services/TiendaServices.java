package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Tienda;
import com.aspsols.cotizaciones.repositories.EmbalajeRepository;
import com.aspsols.cotizaciones.repositories.TiendaRepository;

@Service
public class TiendaServices {

	@Autowired
	TiendaRepository repository;
	
	public List<Tienda> getProducts(String emp, String rama, String imgprincipal){
		return repository.QueryProducts(emp, rama, imgprincipal);
	}
}
