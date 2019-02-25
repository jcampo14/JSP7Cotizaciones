package com.aspsols.cotizaciones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.ClaseId;

@Entity
@Table(name = "CLASE")
@IdClass(ClaseId.class)
public class Clase {

	@Id
	@Column(name = "C_EMP")
	private String cEmp;
	
	@Id
	@Column(name = "CLASE")
	private String clase;
	
	@Column(name = "NOM")
	private String nom;
	
	@Column(name = "AF_INVENT")
	private String afInvent;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAfInvent() {
		return afInvent;
	}

	public void setAfInvent(String afInvent) {
		this.afInvent = afInvent;
	} 
	
	
}
