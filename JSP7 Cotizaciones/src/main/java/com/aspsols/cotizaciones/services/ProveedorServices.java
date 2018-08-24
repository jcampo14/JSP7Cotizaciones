package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Proveedor;
import com.aspsols.cotizaciones.model.ProveedorId;
import com.aspsols.cotizaciones.repositories.ProveedorRepository;

@Service
public class ProveedorServices {

	@Autowired
	private ProveedorRepository repository;

	public void insert(Proveedor object) {
		repository.save(object);
	}

	public boolean update(Proveedor object) {
		boolean response = false;
		ProveedorId id = new ProveedorId();
		id.setcEmp(object.getcEmp());
		id.setnIde(object.getnIde());
		Proveedor regExiste = repository.findOne(id);
		if (regExiste == null) {
			response = false;
		} else {
			response = true;
			repository.save(object);
		}
		return response;
	}
	
	public List<Proveedor> findAll(){
		return (List<Proveedor>) repository.findAll();
	}
	
	public void delete(Proveedor object) {
		repository.delete(object);		
	}
}
