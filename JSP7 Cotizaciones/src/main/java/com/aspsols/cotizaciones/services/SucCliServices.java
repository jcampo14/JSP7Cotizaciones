package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.SucCli;
import com.aspsols.cotizaciones.repositories.SucCliRepository;

@Service
public class SucCliServices {

	@Autowired
	private SucCliRepository repository;

	public List<SucCli> findByNit(String empresa, String nit) {
		return (List<SucCli>) repository.findByNit(empresa, nit);
	}
}
