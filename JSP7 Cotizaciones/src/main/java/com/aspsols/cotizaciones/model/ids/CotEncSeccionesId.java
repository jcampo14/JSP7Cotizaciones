package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class CotEncSeccionesId implements Serializable {

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
	private Long rev;

	@Column(name = "COD_SECCION")
	private String codSeccion;

	public CotEncSeccionesId(String cEmp, String per, String cAgr, String cot, Long rev, String codSeccion) {
		super();
		this.cEmp = cEmp;
		this.per = per;
		this.cAgr = cAgr;
		this.cot = cot;
		this.rev = rev;
		this.codSeccion = codSeccion;
	}

	public CotEncSeccionesId() {
		super();
	}

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

	public Long getRev() {
		return rev;
	}

	public void setRev(Long rev) {
		this.rev = rev;
	}

	public String getCodSeccion() {
		return codSeccion;
	}

	public void setCodSeccion(String codSeccion) {
		this.codSeccion = codSeccion;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CotEncSeccionesId that = (CotEncSeccionesId) o;
		return codSeccion.equals(that.codSeccion) && cAgr.equals(that.cAgr) && per.equals(that.per)
				&& cot.equals(that.cot) && rev.equals(that.rev) && cEmp.equals(that.cEmp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getCodSeccion(), getPer(), getcAgr(), getCot(), getRev());
	}

}
