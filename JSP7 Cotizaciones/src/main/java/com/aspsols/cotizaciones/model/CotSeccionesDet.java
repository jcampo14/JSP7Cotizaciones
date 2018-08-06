package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "COT_SECCIONES_DET")
@IdClass(CotSeccionesDetId.class)
public class CotSeccionesDet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "COD_SECCION")
	private String codSeccion;

	@Id
	@Column(name = "COD_VAL")
	private String codVal;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "VERSION")
	private Integer version;

	public CotSeccionesDet(String cEmp, String codSeccion, String codVal, String descripcion, Integer version) {
		super();
		this.cEmp = cEmp;
		this.codSeccion = codSeccion;
		this.codVal = codVal;
		this.descripcion = descripcion;
		this.version = version;
	}

	public CotSeccionesDet() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getCodSeccion() {
		return codSeccion;
	}

	public void setCodSeccion(String codSeccion) {
		this.codSeccion = codSeccion;
	}

	public String getCodVal() {
		return codVal;
	}

	public void setCodVal(String codVal) {
		this.codVal = codVal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
