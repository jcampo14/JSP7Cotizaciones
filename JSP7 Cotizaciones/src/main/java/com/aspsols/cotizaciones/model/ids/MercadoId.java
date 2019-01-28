package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;

import javax.persistence.Column;

public class MercadoId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "MERC")
	private String merc;

	public MercadoId() {
		super();
	}

	public MercadoId(String cEmp, String merc) {
		super();
		this.cEmp = cEmp;
		this.merc = merc;
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getMerc() {
		return merc;
	}

	public void setMerc(String merc) {
		this.merc = merc;
	}

}
