package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.EmbalajeSinonimoId;

@Entity
@Table(name = "EMBALAJE_SINONIMO")
@IdClass(EmbalajeSinonimoId.class)
public class EmbalajeSinonimo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "COD_EMB")
	private String codEmb;

	@Id
	@Column(name = "IDIOMA")
	private String idioma;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getCodEmb() {
		return codEmb;
	}

	public void setCodEmb(String codEmb) {
		this.codEmb = codEmb;
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

}
