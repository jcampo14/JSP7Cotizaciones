package com.aspsols.cotizaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.Menu;
import com.aspsols.cotizaciones.repositories.MenuRepository;

@Service
public class MenuServices {

	@Autowired
	private MenuRepository repository;
	
	public List<Menu> findByRoot(){
		return (List<Menu>) repository.findByRoot();
	}
}
