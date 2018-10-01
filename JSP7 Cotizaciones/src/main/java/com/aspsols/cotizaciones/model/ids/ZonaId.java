package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class ZonaId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "ZON")
	private String zon;

	public ZonaId(String cEmp, String zon) {
		super();
		this.cEmp = cEmp;
		this.zon = zon;
	}

	public ZonaId() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getZon() {
		return zon;
	}

	public void setZon(String zon) {
		this.zon = zon;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ZonaId that = (ZonaId) o;		
		return cEmp.equals(that.cEmp) && zon.equals(that.zon);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getZon());
	}

}
