package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.FacCostosCotId;

@Entity
@Table(name = "FAC_COSTOS_COT")
@IdClass(FacCostosCotId.class)
public class FacCostosCot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "C_AGR")
	private String cAgr;

	@Id
	@Column(name = "PER")
	private String per;

	@Id
	@Column(name = "NUM_DOC")
	private String numDoc;

	@Id
	@Column(name = "REV")
	private Integer rev;

	@Id
	@Column(name = "COD_COSTO")
	private String codCosto;

	@Column(name = "VALOR")
	private Double valor;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getcAgr() {
		return cAgr;
	}

	public void setcAgr(String cAgr) {
		this.cAgr = cAgr;
	}

	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	public String getCodCosto() {
		return codCosto;
	}

	public void setCodCosto(String codCosto) {
		this.codCosto = codCosto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
