package com.aspsols.cotizaciones.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.CotEncId;

@Entity
@Table(name = "COT_ENC")
@IdClass(CotEncId.class)
public class CotEnc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "PER")
	private String per;

	@Id
	@Column(name = "C_AGR")
	private String cAgr;

	@Id
	@Column(name = "COT")
	private String cot;

	@Id
	@Column(name = "REV")
	private Double rev;

	@Column(name = "EMI")
	private Date emi;

	@Column(name = "TOTD")
	private Double totd;

	@Column(name = "N_IDE")
	private String nIde;

	@Column(name = "C_VEN")
	private String cVen;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false),
			@JoinColumn(name = "N_IDE", referencedColumnName = "N_IDE", insertable = false, updatable = false) })
	private Nits cliente;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false),
			@JoinColumn(name = "C_VEN", referencedColumnName = "N_IDE", insertable = false, updatable = false) })
	private Nits vendedor;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	public String getcAgr() {
		return cAgr;
	}

	public void setcAgr(String cAgr) {
		this.cAgr = cAgr;
	}

	public String getCot() {
		return cot;
	}

	public void setCot(String cot) {
		this.cot = cot;
	}

	public Double getRev() {
		return rev;
	}

	public void setRev(Double rev) {
		this.rev = rev;
	}

	public Date getEmi() {
		return emi;
	}

	public void setEmi(Date emi) {
		this.emi = emi;
	}

	public Double getTotd() {
		return totd;
	}

	public void setTotd(Double totd) {
		this.totd = totd;
	}

	public String getnIde() {
		return nIde;
	}

	public void setnIde(String nIde) {
		this.nIde = nIde;
	}

	public String getcVen() {
		return cVen;
	}

	public void setcVen(String cVen) {
		this.cVen = cVen;
	}

	public Nits getCliente() {
		return cliente;
	}

	public void setCliente(Nits cliente) {
		this.cliente = cliente;
	}

	public Nits getVendedor() {
		return vendedor;
	}

	public void setVendedor(Nits vendedor) {
		this.vendedor = vendedor;
	}

}
