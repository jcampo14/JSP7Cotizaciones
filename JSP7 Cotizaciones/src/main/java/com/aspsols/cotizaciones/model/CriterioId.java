package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;

public class CriterioId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "CRI")
	private String cri;

	public CriterioId(String cEmp, String cri) {
		super();
		this.cEmp = cEmp;
		this.cri = cri;
	}

	public CriterioId() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getCri() {
		return cri;
	}

	public void setCri(String cri) {
		this.cri = cri;
	}

}
