package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class AgenciaId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "C_AGE")
	private String cAge;

	public AgenciaId(String cEmp, String cAge) {
		super();
		this.cEmp = cEmp;
		this.cAge = cAge;
	}

	public AgenciaId() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getcAge() {
		return cAge;
	}

	public void setcAge(String cAge) {
		this.cAge = cAge;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AgenciaId that = (AgenciaId) o;		
		return cEmp.equals(that.cEmp) && cAge.equals(that.cAge);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(getcEmp(), getcAge());
    }

}
