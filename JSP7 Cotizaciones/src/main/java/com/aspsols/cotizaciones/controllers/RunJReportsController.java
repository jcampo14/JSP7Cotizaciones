package com.aspsols.cotizaciones.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspsols.cotizaciones.responses.StandardResponse;
import com.aspsols.cotizaciones.utilities.RunReportsAddressCustom;

@RestController
public class RunJReportsController {

	private static final String SERVICE_PATH = "/runJReports";

	@RequestMapping(method = RequestMethod.GET, path = SERVICE_PATH)
	public StandardResponse getNode(@RequestParam("reportName") String reportName) {
		return new StandardResponse(RunReportsAddressCustom.getPriorityNode(reportName));
	}
}
