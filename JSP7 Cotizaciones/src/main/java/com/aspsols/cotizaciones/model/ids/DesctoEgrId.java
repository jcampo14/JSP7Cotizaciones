package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;

import javax.persistence.Column;

public class DesctoEgrId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "C_DES")
	private String cDes;

	public DesctoEgrId(String cEmp, String cDes) {
		super();
		this.cEmp = cEmp;
		this.cDes = cDes;
	}

	public DesctoEgrId() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getcDes() {
		return cDes;
	}

	public void setcDes(String cDes) {
		this.cDes = cDes;
	}

}
