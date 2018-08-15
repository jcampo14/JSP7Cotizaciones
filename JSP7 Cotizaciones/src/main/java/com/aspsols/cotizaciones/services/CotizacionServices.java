package com.aspsols.cotizaciones.services;

import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.request.CotizacionRequest;
import com.aspsols.cotizaciones.responses.ProcessResponse;

@Service
public class CotizacionServices {

	public ProcessResponse<CotizacionRequest> createCotizacion(CotizacionRequest model) {
		ProcessResponse<CotizacionRequest> response = new ProcessResponse<>();
		
		return response;
	}
}
