package com.aspsols.cotizaciones.responses;

import java.util.List;

import com.aspsols.cotizaciones.jsonviewer.JSonServiceViewer;
import com.fasterxml.jackson.annotation.JsonView;

public class QueryResponse<E> {

	@JsonView(JSonServiceViewer.Public.class)
	private List<E> data;

	@JsonView(JSonServiceViewer.Public.class)
	private long count;

	public QueryResponse() {
		super();
	}

	public QueryResponse(List<E> data, long count) {
		super();
		this.data = data;
		this.count = count;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
