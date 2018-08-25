package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.IdiomaId;

@Entity
@Table(name = "IDIOMA")
@IdClass(IdiomaId.class)
public class Idioma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "IDIOMA")
	private String idioma;

	@Column(name = "VERSION")
	private Integer version;

	public Idioma(String cEmp, String idioma, Integer version) {
		super();
		this.cEmp = cEmp;
		this.idioma = idioma;
		this.version = version;
	}

	public Idioma() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
