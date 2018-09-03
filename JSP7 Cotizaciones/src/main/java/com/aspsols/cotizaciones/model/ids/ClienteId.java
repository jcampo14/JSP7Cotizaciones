package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class ClienteId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "N_IDE")
	private String nIde;

	public ClienteId(String cEmp, String nIde) {
		super();
		this.cEmp = cEmp;
		this.nIde = nIde;
	}

	public ClienteId() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getnIde() {
		return nIde;
	}

	public void setnIde(String nIde) {
		this.nIde = nIde;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ClienteId that = (ClienteId) o;		
		return cEmp.equals(that.cEmp) && nIde.equals(that.nIde);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getnIde());
	}

}
