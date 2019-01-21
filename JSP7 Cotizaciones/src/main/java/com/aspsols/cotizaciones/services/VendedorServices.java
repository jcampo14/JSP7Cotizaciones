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
	
	public Vendedor findByNit(String nIde, String empresa) {
		return repository.findByNit(nIde, empresa);
	}
	
	public Vendedor findByUsuario(String usuario, String empresa) {
		return repository.findByUsuario(empresa, usuario);
	}
}
