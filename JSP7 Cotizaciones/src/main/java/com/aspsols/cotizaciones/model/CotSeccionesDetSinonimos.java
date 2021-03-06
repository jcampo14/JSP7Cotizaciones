package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.CotSeccionesDetSinonimosId;

@Entity
@Table(name = "COT_SECCIONES_DET_SINONIMOS")
@IdClass(CotSeccionesDetSinonimosId.class)
public class CotSeccionesDetSinonimos implements Serializable{

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

	@Id
	@Column(name = "IDIOMA")
	private String idioma;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "VERSION")
	private Integer version;

	public CotSeccionesDetSinonimos(String cEmp, String codSeccion, String codVal, String idioma, String descripcion,
			Integer version) {
		super();
		this.cEmp = cEmp;
		this.codSeccion = codSeccion;
		this.codVal = codVal;
		this.idioma = idioma;
		this.descripcion = descripcion;
		this.version = version;
	}

	public CotSeccionesDetSinonimos() {
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

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
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
