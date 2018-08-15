package com.aspsols.cotizaciones.controllers;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.model.Menu;
import com.aspsols.cotizaciones.responses.MenuResponse;
import com.aspsols.cotizaciones.services.MenuServices;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class MenuController {

	private static final String SERVICE_PATH = "/menu/";

	@Autowired
	private MenuServices service;

	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public MenuResponse<Menu> findByRoot() throws JSONException, JsonProcessingException {
		MenuResponse<Menu> response = new MenuResponse<>();
		List<Menu> resultData = service.findByRoot();
		response.setData(resultData);
		return response;
	}

}
