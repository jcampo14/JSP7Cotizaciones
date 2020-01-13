package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Departamento;
import com.aspsols.cotizaciones.repositories.DepartamentoRepository;

@Service
public class DepartamentoServices {

	@Autowired
	private DepartamentoRepository repository;

	public List<Departamento> findByPais(String empresa, String pais) {
		return repository.findByPais(empresa, pais);
	}
}
