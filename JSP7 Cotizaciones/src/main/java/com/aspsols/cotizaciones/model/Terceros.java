package com.aspsols.cotizaciones.model;

import java.io.Serializable;

public class Terceros implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cEmp;

	private String nIde;

	private String nombre;

	public Terceros(String cEmp, String nIde, String nombre) {
		super();
		this.cEmp = cEmp;
		this.nIde = nIde;
		this.nombre = nombre;
	}

	public Terceros() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getnIde() {
		return nIde;
	}

	public void setnIde(String nIde) {
		this.nIde = nIde;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
