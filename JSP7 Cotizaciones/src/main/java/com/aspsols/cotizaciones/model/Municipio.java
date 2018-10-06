package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.MunicipioId;

@Entity
@Table(name = "MUNICIPIO")
@IdClass(MunicipioId.class)
public class Municipio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "C_PAI")
	private String cPai;

	@Id
	@Column(name = "C_DPTO")
	private String cDpto;

	@Id
	@Column(name = "C_MNPO")
	private String cMnpo;

	@Column(name = "NOMBRE")
	private String nombre;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getcPai() {
		return cPai;
	}

	public void setcPai(String cPai) {
		this.cPai = cPai;
	}

	public String getcDpto() {
		return cDpto;
	}

	public void setcDpto(String cDpto) {
		this.cDpto = cDpto;
	}

	public String getcMnpo() {
		return cMnpo;
	}

	public void setcMnpo(String cMnpo) {
		this.cMnpo = cMnpo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
