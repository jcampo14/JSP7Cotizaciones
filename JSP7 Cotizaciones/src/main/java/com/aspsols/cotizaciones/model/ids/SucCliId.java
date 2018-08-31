package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class SucCliId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "N_IDE")
	private String nIde;

	@Column(name = "C_SUC")
	private String cSuc;

	public SucCliId(String cEmp, String nIde, String cSuc) {
		super();
		this.cEmp = cEmp;
		this.nIde = nIde;
		this.cSuc = cSuc;
	}

	public SucCliId() {
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

	public String getcSuc() {
		return cSuc;
	}

	public void setcSuc(String cSuc) {
		this.cSuc = cSuc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SucCliId that = (SucCliId) o;
		return nIde.equals(that.nIde) && cEmp.equals(that.cEmp) && cSuc.equals(that.cSuc);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getnIde(), getcSuc());
	}

}
