package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class DesctoEgrId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "C_DES")
	private String cDes;

	public DesctoEgrId(String cEmp, String cDes) {
		super();
		this.cEmp = cEmp;
		this.cDes = cDes;
	}

	public DesctoEgrId() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getcDes() {
		return cDes;
	}

	public void setcDes(String cDes) {
		this.cDes = cDes;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DesctoEgrId that = (DesctoEgrId) o;
		return cDes.equals(that.cDes) && cEmp.equals(that.cEmp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getcDes());
	}

}
