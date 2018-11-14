package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;
import java.util.Objects;

public class EmbalajeSinonimoId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cEmp;

	private String codEmb;

	private String idioma;

	public EmbalajeSinonimoId() {
		super();
	}

	public EmbalajeSinonimoId(String cEmp, String codEmb, String idioma) {
		super();
		this.cEmp = cEmp;
		this.codEmb = codEmb;
		this.idioma = idioma;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EmbalajeSinonimoId that = (EmbalajeSinonimoId) o;
		return codEmb.equals(that.codEmb) && idioma.equals(that.idioma) && cEmp.equals(that.cEmp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getcEmp(), getCodEmb(), getIdioma());
	}

}
