package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class MunicipioId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "C_PAI")
	private String cPai;

	@Column(name = "C_DPTO")
	private String cDpto;

	@Column(name = "C_MNPO")
	private String cMnpo;

	public MunicipioId() {
		super();
	}

	public MunicipioId(String cEmp, String cPai, String cDpto, String cMnpo) {
		super();
		this.cEmp = cEmp;
		this.cPai = cPai;
		this.cDpto = cDpto;
		this.cMnpo = cMnpo;
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getcPai() {
		return cPai;
	}

	public void setcPai(String cPai) {
		this.cPai = cPai;
	}

	public String getcDpto() {
		return cDpto;
	}

	public void setcDpto(String cDpto) {
		this.cDpto = cDpto;
	}

	public String getcMnpo() {
		return cMnpo;
	}

	public void setcMnpo(String cMnpo) {
		this.cMnpo = cMnpo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MunicipioId that = (MunicipioId) o;
		return cMnpo.equals(that.cMnpo) && cDpto.equals(that.cDpto) && cPai.equals(that.cPai) && cEmp.equals(that.cEmp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cEmp, cMnpo, cDpto, cPai);
	}

}
