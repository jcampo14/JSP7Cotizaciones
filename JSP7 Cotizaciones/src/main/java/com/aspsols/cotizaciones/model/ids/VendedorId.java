package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;

import javax.persistence.Column;

public class VendedorId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "N_IDE")
	private String nIde;

	public VendedorId(String cEmp, String nIde) {
		super();
		this.cEmp = cEmp;
		this.nIde = nIde;
	}

	public VendedorId() {
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
