package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Vendedor;
import com.aspsols.cotizaciones.repositories.VendedorRepository;

@Service
public class VendedorServices {

	@Autowired
	private VendedorRepository repository;

	public List<Vendedor> findAll(String empresa) {
		return repository.findByEmpresa(empresa);
	}
}
