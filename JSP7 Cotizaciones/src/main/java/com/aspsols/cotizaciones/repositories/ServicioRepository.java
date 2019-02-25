package com.aspsols.cotizaciones.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aspsols.cotizaciones.model.Servicio;
import com.aspsols.cotizaciones.model.ids.ServicioId;

public interface ServicioRepository extends CrudRepository<Servicio, ServicioId> {	
}
