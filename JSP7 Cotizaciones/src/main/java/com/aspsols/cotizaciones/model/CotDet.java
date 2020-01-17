package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.CotDetId;

@Entity
@Table(name = "COT_DET")
@IdClass(CotDetId.class)
public class CotDet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	private Integer rev;

	@Id
	@Column(name = "COD")
	private String cod;

	@Column(name = "CAN")
	private Double can;

	@Column(name = "LIS")
	private Double lis;

	@Column(name = "VEN")
	private Double ven;

	@Column(name = "P_DES")
	private Double pDes;

	@Column(name = "INF7")
	private String inf7;

	@Column(name = "NOM")
	private String nom;

	@Column(name = "ORDEN")
	private Integer orden;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "COD", referencedColumnName = "COD", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private Articulo articulo;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public Double getCan() {
		return can;
	}

	public void setCan(Double can) {
		this.can = can;
	}

	public Double getLis() {
		return lis;
	}

	public void setLis(Double lis) {
		this.lis = lis;
	}

	public Double getVen() {
		return ven;
	}

	public void setVen(Double ven) {
		this.ven = ven;
	}

	public Double getpDes() {
		return pDes;
	}

	public void setpDes(Double pDes) {
		this.pDes = pDes;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
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

	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	public String getInf7() {
		return inf7;
	}

	public void setInf7(String inf7) {
		this.inf7 = inf7;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

}
