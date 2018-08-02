package com.aspsols.cotizaciones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PARAMETROS")
public class Parametros {
	
	@Id
	@Column(name = "C_EMP")
	private String cEmp;
	
	@Column(name = "EMP")
	private String emp;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getEmp() {
		return emp;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}
		
}
