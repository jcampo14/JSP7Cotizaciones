package com.aspsols.cotizaciones.responses;

import com.aspsols.cotizaciones.jsonviewer.JSonServiceViewer;
import com.fasterxml.jackson.annotation.JsonView;

public class ProcessResponse<E> {

	@JsonView(JSonServiceViewer.Public.class)
	private boolean success;

	@JsonView(JSonServiceViewer.Public.class)
	private String message;

	public ProcessResponse() {
		super();
	}

	public ProcessResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
