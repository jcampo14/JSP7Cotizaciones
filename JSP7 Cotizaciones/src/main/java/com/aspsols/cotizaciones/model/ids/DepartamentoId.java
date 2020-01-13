package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

public class DepartamentoId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cEmp;
	private String cPai;
	private String cDpto;

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

	public String getcDpto() {
		return cDpto;
	}

	public void setcDpto(String cDpto) {
		this.cDpto = cDpto;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DepartamentoId that = (DepartamentoId) o;
		return cDpto.equals(that.cDpto) && cPai.equals(that.cPai) && cEmp.equals(that.cEmp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getcPai(), getcDpto());
	}

}
