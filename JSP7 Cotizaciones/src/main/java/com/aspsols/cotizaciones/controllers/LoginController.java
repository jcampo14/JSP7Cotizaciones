package com.aspsols.cotizaciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsols.cotizaciones.services.LoginServices;

@RestController
public class LoginController {

	private static final String SERVICE_PATH = "/loginService";

	@Autowired
	private LoginServices service;

	@RequestMapping(method = RequestMethod.GET, value = SERVICE_PATH)
	public ProcessResponse login(@RequestParam("usuario") String usuario, @RequestParam("emp") String empresa,
			@RequestParam("clave") String clave) {
		return service.login(usuario, clave, empresa);
	}
}
