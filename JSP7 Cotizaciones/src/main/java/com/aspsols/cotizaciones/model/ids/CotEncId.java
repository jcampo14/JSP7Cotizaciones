package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;

import javax.persistence.Column;

public class CotEncId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "PER")
	private String per;

	@Column(name = "C_AGR")
	private String cAgr;

	@Column(name = "COT")
	private String cot;

	@Column(name = "REV")
	private Double rev;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	public String getcAgr() {
		return cAgr;
	}

	public void setcAgr(String cAgr) {
		this.cAgr = cAgr;
	}

	public String getCot() {
		return cot;
	}

	public void setCot(String cot) {
		this.cot = cot;
	}

	public Double getRev() {
		return rev;
	}

	public void setRev(Double rev) {
		this.rev = rev;
	}

}
