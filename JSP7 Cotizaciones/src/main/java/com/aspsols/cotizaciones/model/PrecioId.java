package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;

public class PrecioId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "COD")
	private String cod;

	@Column(name = "CRI")
	private String cri;

	public PrecioId(String cEmp, String cod, String cri) {
		super();
		this.cEmp = cEmp;
		this.cod = cod;
		this.cri = cri;
	}

	public PrecioId() {
		super();
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

	public String getCri() {
		return cri;
	}

	public void setCri(String cri) {
		this.cri = cri;
	}

}
