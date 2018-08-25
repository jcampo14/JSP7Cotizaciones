package com.aspsols.cotizaciones.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aspsols.cotizaciones.model.ProspectoCliente;
import com.aspsols.cotizaciones.model.ids.ProspectoClienteId;

public interface ProspectoClienteRepository extends CrudRepository<ProspectoCliente, ProspectoClienteId> {

}
