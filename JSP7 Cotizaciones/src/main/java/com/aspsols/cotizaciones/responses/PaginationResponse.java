package com.aspsols.cotizaciones.responses;

import java.io.Serializable;
import java.util.List;

public class PaginationResponse<E> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<E> data;

	private Long totalElements;

	public PaginationResponse(List<E> data, Long totalElements) {
		super();
		this.data = data;
		this.totalElements = totalElements;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

}
