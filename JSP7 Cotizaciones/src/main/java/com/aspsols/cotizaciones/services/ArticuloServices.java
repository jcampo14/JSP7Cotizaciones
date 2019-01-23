package com.aspsols.cotizaciones.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Articulo;
import com.aspsols.cotizaciones.repositories.ArticuloRepository;
import com.aspsols.cotizaciones.responses.PaginationResponse;

@Service
public class ArticuloServices {

	@Autowired
	private ArticuloRepository repository;

	public List<Articulo> showByEmpresa(String empresa, String filtro) {
		return (List<Articulo>) repository.findByEmpresa(empresa, filtro);
	}

	public PaginationResponse<Articulo> showByEmpresa(String empresa, String filtro, int page, int size) {
		List<Articulo> resultData = new ArrayList<>();
		Page<Articulo> pageResult = repository.findByEmpresa(empresa, filtro, new PageRequest(page - 1, size));		
		pageResult.forEach(resultData::add);		
		return new PaginationResponse<>(resultData, pageResult.getTotalElements());
	}
	
	public List<Articulo> findByCodigo(String empresa, String codigo){
		return repository.findByCodigo(empresa, codigo);
	}
}
