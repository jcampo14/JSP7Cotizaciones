package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Articulo;
import com.aspsols.cotizaciones.repositories.ArticuloRepository;

@Service
public class ArticuloServices {

	@Autowired
	private ArticuloRepository repository;
	
	public List<Articulo> showByEmpresa(String empresa, String filtro) {
		return (List<Articulo>) repository.findByEmpresa(empresa, filtro);
	}
}
