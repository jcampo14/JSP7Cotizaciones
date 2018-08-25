package com.aspsols.cotizaciones.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aspsols.cotizaciones.model.UsuAge;
import com.aspsols.cotizaciones.model.ids.UsuAgeId;

public interface UsuAgeRepository extends CrudRepository<UsuAge, UsuAgeId>{

}
