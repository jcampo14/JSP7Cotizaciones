package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;

import javax.persistence.Column;


public class EmbalajeId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="C_EMP")
	private String cEmp;
	
	@Column(name="COD_EMB")
	private String cEmb;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getcEmb() {
		return cEmb;
	}

	public void setcEmb(String cEmb) {
		this.cEmb = cEmb;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
