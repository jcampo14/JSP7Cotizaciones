package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class IncotermFacCostosAdicId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "ID_INCOTERM")
	private String idIncoterm;

	@Column(name = "ID_FAC_COSTOS_ADIC")
	private String idFacCostosAdic;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getIdIncoterm() {
		return idIncoterm;
	}

	public void setIdIncoterm(String idIncoterm) {
		this.idIncoterm = idIncoterm;
	}

	public String getIdFacCostosAdic() {
		return idFacCostosAdic;
	}

	public void setIdFacCostosAdic(String idFacCostosAdic) {
		this.idFacCostosAdic = idFacCostosAdic;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		IncotermFacCostosAdicId that = (IncotermFacCostosAdicId) o;
		return idIncoterm.equals(that.idIncoterm) && idFacCostosAdic.equals(that.idFacCostosAdic)
				&& cEmp.equals(that.cEmp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getIdFacCostosAdic(), getIdIncoterm());
	}

}
