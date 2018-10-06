package com.aspsols.cotizaciones.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aspsols.cotizaciones.model.Municipio;
import com.aspsols.cotizaciones.model.ids.MunicipioId;

public interface MunicipioRepository extends CrudRepository<Municipio, MunicipioId> {

}
