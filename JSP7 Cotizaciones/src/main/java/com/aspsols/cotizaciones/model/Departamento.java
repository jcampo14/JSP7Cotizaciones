package com.aspsols.cotizaciones.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.DepartamentoId;
import com.aspsolutions.jdbc.annotations.Column;

@Entity
@Table(name = "DEPARTAMENTO")
@IdClass(DepartamentoId.class)
public class Departamento {

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "C_PAI")
	private String cPai;

	@Id
	@Column(name = "C_DPTO")
	private String cDpto;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
