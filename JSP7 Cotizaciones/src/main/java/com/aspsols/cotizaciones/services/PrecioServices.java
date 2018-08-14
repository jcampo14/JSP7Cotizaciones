package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Precio;
import com.aspsols.cotizaciones.model.PrecioId;
import com.aspsols.cotizaciones.repositories.PrecioRepository;

@Service
public class PrecioServices {

	@Autowired
	private PrecioRepository repository;
	
	public Precio findById(PrecioId id) {
		return repository.findOne(id);
	}
	
	public List<Precio> findByArticulo(String empresa, String articulo){
		return (List<Precio>) repository.findByArticulo(empresa, articulo);
	}
	
}
