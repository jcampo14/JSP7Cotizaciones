package com.aspsols.cotizaciones.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Servicio;
import com.aspsols.cotizaciones.model.ids.ServicioId;
import com.aspsols.cotizaciones.repositories.ServicioRepository;

@Service
public class ServicioServices {

	@Autowired
	private ServicioRepository repository;

	public Servicio findById(String empresa, String articulo, String moneda) {
		ServicioId id = new ServicioId(empresa, articulo, moneda);
		return repository.findOne(id);
	}

}
