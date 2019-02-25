package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

public class ServicioId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cEmp;

	private String cod;

	private String mon;

	public ServicioId() {
		super();
	}

	public ServicioId(String cEmp, String cod, String mon) {
		super();
		this.cEmp = cEmp;
		this.cod = cod;
		this.mon = mon;
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

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ServicioId that = (ServicioId) o;		
		return cEmp.equals(that.cEmp) && cod.equals(that.cod) && mon.equals(that.mon);		
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getCod(), getMon());
	}

}
