package com.aspsols.cotizaciones.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aspsols.cotizaciones.model.Nits;
import com.aspsols.cotizaciones.model.NitsId;

public interface NitsRepository extends CrudRepository<Nits, NitsId>{

}
