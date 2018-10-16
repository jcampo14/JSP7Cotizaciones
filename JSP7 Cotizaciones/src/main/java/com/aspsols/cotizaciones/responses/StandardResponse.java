package com.aspsols.cotizaciones.responses;

import java.io.Serializable;

public class StandardResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String data;

	public StandardResponse(String data) {
		super();
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
