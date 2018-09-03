package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class CotSeccionesSinonimosId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "COD_SECCION")
	private String codSeccion;

	@Column(name = "IDIOMA")
	private String idioma;

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

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CotSeccionesSinonimosId that = (CotSeccionesSinonimosId) o;
		return codSeccion.equals(that.codSeccion) && cEmp.equals(that.cEmp) && idioma.equals(that.idioma);		
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getCodSeccion(), getIdioma());
	}

}
