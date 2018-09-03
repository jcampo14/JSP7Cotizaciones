package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;


public class EmbalajeId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="C_EMP")
	private String cEmp;
	
	@Column(name="COD_EMB")
	private String cEmb;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getcEmb() {
		return cEmb;
	}

	public void setcEmb(String cEmb) {
		this.cEmb = cEmb;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EmbalajeId that = (EmbalajeId) o;
		return cEmb.equals(that.cEmb) && cEmp.equals(that.cEmp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getcEmb());
	}
}
