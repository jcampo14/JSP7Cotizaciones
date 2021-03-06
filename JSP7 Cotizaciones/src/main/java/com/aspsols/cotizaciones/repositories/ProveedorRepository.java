package com.aspsols.cotizaciones.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aspsols.cotizaciones.model.Proveedor;
import com.aspsols.cotizaciones.model.ids.ProveedorId;

public interface ProveedorRepository extends CrudRepository<Proveedor, ProveedorId>{

}
