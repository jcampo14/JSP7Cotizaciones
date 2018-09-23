package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class FacCostosCotId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "C_AGR")
	private String cAgr;

	@Column(name = "PER")
	private String per;

	@Column(name = "NUM_DOC")
	private String numDoc;

	@Column(name = "REV")
	private Integer rev;

	@Column(name = "COD_COSTO")
	private String codCosto;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getcAgr() {
		return cAgr;
	}

	public void setcAgr(String cAgr) {
		this.cAgr = cAgr;
	}

	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	public String getCodCosto() {
		return codCosto;
	}

	public void setCodCosto(String codCosto) {
		this.codCosto = codCosto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FacCostosCotId that = (FacCostosCotId) o;
		return numDoc.equals(that.numDoc) && rev.equals(that.rev) && cAgr.equals(that.cAgr) && per.equals(that.per)
				&& codCosto.equals(that.codCosto) && cEmp.equals(that.cEmp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getCodCosto(), getNumDoc(), getRev(), getcAgr(), getPer());
	}

}
