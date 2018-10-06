package com.aspsols.cotizaciones.model.lists;

import java.io.Serializable;
import java.util.List;

import com.aspsols.cotizaciones.model.ArticuloDesCom;

public class ArticuloDesComList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ArticuloDesCom> list;

	public List<ArticuloDesCom> getList() {
		return list;
	}

	public void setList(List<ArticuloDesCom> list) {
		this.list = list;
	}

}
