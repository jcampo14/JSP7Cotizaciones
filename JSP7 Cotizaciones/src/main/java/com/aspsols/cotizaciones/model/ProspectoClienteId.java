package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;

public class ProspectoClienteId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "N_IDE")
	private String nIde;

	public ProspectoClienteId(String cEmp, String nIde) {
		super();
		this.cEmp = cEmp;
		this.nIde = nIde;
	}

	public ProspectoClienteId() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getnIde() {
		return nIde;
	}

	public void setnIde(String nIde) {
		this.nIde = nIde;
	}

}
