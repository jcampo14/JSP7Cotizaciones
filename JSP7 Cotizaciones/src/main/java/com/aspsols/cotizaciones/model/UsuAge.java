package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "USU_AGE")
@IdClass(UsuAgeId.class)
public class UsuAge implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USUARIO")
	private String usuario;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "VERSION")
	private Integer version;

	public UsuAge(String usuario, String cEmp, Integer version) {
		super();
		this.usuario = usuario;
		this.cEmp = cEmp;
		this.version = version;
	}

	public UsuAge() {
		super();
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
