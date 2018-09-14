package com.aspsols.cotizaciones.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.repositories.CotizacionRepository;
import com.aspsols.cotizaciones.request.CotizacionRequest;

@Service
public class CotizacionServices {
	
	@Autowired
	private CotizacionRepository repository;

	public boolean insertTemporalTable(CotizacionRequest model, String idTransaccion) {		
		repository.create(model, idTransaccion);		
		return true;
	}
	
	public void deleteTemporalTable(String idTransaccion) {
		repository.delete(idTransaccion);
	}
}
