package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;

import javax.persistence.Column;

public class PaisId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "C_PAI")
	private String cPai;

	public PaisId(String cEmp, String cPai) {
		super();
		this.cEmp = cEmp;
		this.cPai = cPai;
	}

	public PaisId() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getcPai() {
		return cPai;
	}

	public void setcPai(String cPai) {
		this.cPai = cPai;
	}

}
