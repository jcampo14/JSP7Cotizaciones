package com.aspsols.cotizaciones.model.ids;

import java.io.Serializable;

import javax.persistence.Column;

public class UsuAgeId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "USUARIO")
	private String usuario;

	@Column(name = "C_EMP")
	private String cEmp;

	public UsuAgeId(String usuario, String cEmp) {
		super();
		this.usuario = usuario;
		this.cEmp = cEmp;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

}
