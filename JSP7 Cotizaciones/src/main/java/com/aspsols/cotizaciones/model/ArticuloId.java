package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;

public class ArticuloId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "COD")
	private String cod;

	public ArticuloId(String cEmp, String cod) {
		super();
		this.cEmp = cEmp;
		this.cod = cod;
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

}
