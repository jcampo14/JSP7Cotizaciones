package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PARAM_FAC")
public class ParamFac implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "INCP_ARR")
	private int incpArr;

	@Column(name = "INCP_ABJ")
	private int incpAbj;

	@Column(name = "SEGURO_AUT")
	private String seguroAut;

	@Column(name = "PORC_SEG_AUT")
	private Double porcSegAut;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public int getIncpArr() {
		return incpArr;
	}

	public void setIncpArr(int incpArr) {
		this.incpArr = incpArr;
	}

	public int getIncpAbj() {
		return incpAbj;
	}

	public void setIncpAbj(int incpAbj) {
		this.incpAbj = incpAbj;
	}

	public String getSeguroAut() {
		return seguroAut;
	}

	public void setSeguroAut(String seguroAut) {
		this.seguroAut = seguroAut;
	}

	public Double getPorcSegAut() {
		return porcSegAut;
	}

	public void setPorcSegAut(Double porcSegAut) {
		this.porcSegAut = porcSegAut;
	}

}
