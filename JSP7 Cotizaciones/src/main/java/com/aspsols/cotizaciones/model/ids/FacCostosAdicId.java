package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class FacCostosAdicId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "C_EMP")
    private String cEmp;
	
	@Column(name = "CODIGO")
    private String codigo;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}	
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FacCostosAdicId that = (FacCostosAdicId) o;
		return codigo.equals(that.codigo) && cEmp.equals(that.cEmp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getCodigo());
	}
	
}
