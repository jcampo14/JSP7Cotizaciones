package com.aspsols.cotizaciones.responses;

import java.util.List;

import com.aspsols.cotizaciones.jsonviewer.JSonServiceViewer;
import com.fasterxml.jackson.annotation.JsonView;

public class MenuResponse<E> {

	@JsonView(JSonServiceViewer.Public.class)
	private List<E> data;

	public MenuResponse() {
		super();
	}

	public MenuResponse(List<E> data) {
		super();
		this.data = data;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

}
