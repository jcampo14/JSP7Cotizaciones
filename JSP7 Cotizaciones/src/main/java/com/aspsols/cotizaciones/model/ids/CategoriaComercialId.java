package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class CategoriaComercialId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "COD_CAT")
	private String codCat;

	public CategoriaComercialId() {
		super();
	}

	public CategoriaComercialId(String cEmp, String codCat) {
		super();
		this.cEmp = cEmp;
		this.codCat = codCat;
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getCodCat() {
		return codCat;
	}

	public void setCodCat(String codCat) {
		this.codCat = codCat;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CategoriaComercialId that = (CategoriaComercialId) o;		
		return cEmp.equals(that.cEmp) && codCat.equals(that.codCat);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getCodCat());
	}

}
