package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "COT_SECCIONES")
@IdClass(CotSeccionesId.class)
public class CotSecciones implements Serializable {

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

	@Column(name = "NOMBRE_SEC")
	private String nombreSec;

	@Column(name = "TIPO_SECCION")
	private String tipoSeccion;

	@Column(name = "ORDEN")
	private Integer orden;

	public CotSecciones(String cEmp, String codSeccion, String nombreSec, String tipoSeccion, Integer orden) {
		super();
		this.cEmp = cEmp;
		this.codSeccion = codSeccion;
		this.nombreSec = nombreSec;
		this.tipoSeccion = tipoSeccion;
		this.orden = orden;
	}

	public CotSecciones() {
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

	public String getNombreSec() {
		return nombreSec;
	}

	public void setNombreSec(String nombreSec) {
		this.nombreSec = nombreSec;
	}

	public String getTipoSeccion() {
		return tipoSeccion;
	}

	public void setTipoSeccion(String tipoSeccion) {
		this.tipoSeccion = tipoSeccion;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

}
