package com.aspsols.cotizaciones.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspsols.cotizaciones.model.Menu;

public interface MenuRepository extends CrudRepository<Menu, Integer> {

	@Query("FROM Menu WHERE idPadre is null")
	public Iterable<Menu> findByRoot();
}
