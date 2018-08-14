package com.aspsols.cotizaciones.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "PRECIO")
@IdClass(PrecioId.class)
public class Precio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "COD")
	private String cod;

	@Id
	@Column(name = "CRI")
	private String cri;

	@Column(name = "P_VEN")
	private Double pVen;

	@Column(name = "FECHA")
	private Date fecha;

	public Precio() {
		super();
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

	public String getCri() {
		return cri;
	}

	public void setCri(String cri) {
		this.cri = cri;
	}

	public Double getpVen() {
		return pVen;
	}

	public void setpVen(Double pVen) {
		this.pVen = pVen;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
