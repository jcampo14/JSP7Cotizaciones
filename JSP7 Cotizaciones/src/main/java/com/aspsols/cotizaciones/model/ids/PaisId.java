package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class PaisId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "C_PAI")
	private String cPai;

	public PaisId(String cEmp, String cPai) {
		super();
		this.cEmp = cEmp;
		this.cPai = cPai;
	}

	public PaisId() {
		super();
	}

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
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PaisId that = (PaisId) o;		
		return cEmp.equals(that.cEmp) && cPai.equals(that.cPai);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getcPai());
	}

}
