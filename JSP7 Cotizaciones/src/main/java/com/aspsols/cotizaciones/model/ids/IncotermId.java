package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class IncotermId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "COD_INCOTERM")
	private String codIncoterm;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getCodIncoterm() {
		return codIncoterm;
	}

	public void setCodIncoterm(String codIncoterm) {
		this.codIncoterm = codIncoterm;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		IncotermId that = (IncotermId) o;
		return codIncoterm.equals(that.codIncoterm) && cEmp.equals(that.cEmp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cEmp, codIncoterm);
	}

}
