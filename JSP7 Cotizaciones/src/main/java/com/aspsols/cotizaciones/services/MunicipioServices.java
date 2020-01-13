package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Municipio;
import com.aspsols.cotizaciones.repositories.MunicipioRepository;

@Service
public class MunicipioServices {

	@Autowired
	private MunicipioRepository repository;

	public List<Municipio> findAll() {
		return (List<Municipio>) repository.findAll();
	}

	public List<Municipio> findByDepartamento(String empresa, String pais, String departamento) {
		return repository.findByDepartamento(empresa, pais, departamento);
	}
}
