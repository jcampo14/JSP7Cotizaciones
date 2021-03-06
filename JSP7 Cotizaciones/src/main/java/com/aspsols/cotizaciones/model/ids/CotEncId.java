package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

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
	private Integer rev;

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

	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CotEncId that = (CotEncId) o;
		return cEmp.equals(that.cEmp) && per.equals(that.per) && cAgr.equals(that.cAgr) && cot.equals(that.cot)
				&& rev.equals(that.rev);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getPer(), getcAgr(), getCot(), getRev());
	}

}
