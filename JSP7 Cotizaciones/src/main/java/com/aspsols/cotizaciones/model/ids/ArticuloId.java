package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class ArticuloId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "COD")
	private String cod;

	public ArticuloId(String cEmp, String cod) {
		super();
		this.cEmp = cEmp;
		this.cod = cod;
	}

	public ArticuloId() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ArticuloId that = (ArticuloId) o;		
		return cEmp.equals(that.cEmp) && cod.equals(that.cod);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getCod());
	}

}
