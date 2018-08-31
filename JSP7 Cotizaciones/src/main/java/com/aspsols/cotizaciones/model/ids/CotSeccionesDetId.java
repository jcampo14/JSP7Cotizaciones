package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class CotSeccionesDetId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "COD_SECCION")
	private String codSeccion;

	@Column(name = "COD_VAL")
	private String codVal;

	public CotSeccionesDetId(String cEmp, String codSeccion, String codVal) {
		super();
		this.cEmp = cEmp;
		this.codSeccion = codSeccion;
		this.codVal = codVal;
	}

	public CotSeccionesDetId() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getCodSeccion() {
		return codSeccion;
	}

	public void setCodSeccion(String codSeccion) {
		this.codSeccion = codSeccion;
	}

	public String getCodVal() {
		return codVal;
	}

	public void setCodVal(String codVal) {
		this.codVal = codVal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CotSeccionesDetId that = (CotSeccionesDetId) o;
		return codSeccion.equals(that.codSeccion) && codVal.equals(that.codVal) && cEmp.equals(that.cEmp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getCodSeccion(), getCodVal());
	}

}
