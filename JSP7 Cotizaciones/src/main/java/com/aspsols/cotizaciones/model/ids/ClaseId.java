package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

public class ClaseId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cEmp;

	private String clase;

	public ClaseId() {
		super();
	}

	public ClaseId(String cEmp, String clase) {
		super();
		this.cEmp = cEmp;
		this.clase = clase;
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ClaseId that = (ClaseId) o;		
		return cEmp.equals(that.cEmp) && clase.equals(that.clase);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getClase());
	}

}
