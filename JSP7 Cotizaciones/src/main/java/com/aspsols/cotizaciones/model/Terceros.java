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

	private String iva;

	private String zona;

	private String suc;

	private String mercado;

	public Terceros(String cEmp, String nIde, String nombre, String iva, String zona, String suc) {
		super();
		this.cEmp = cEmp;
		this.nIde = nIde;
		this.nombre = nombre;
		this.iva = iva;
		this.zona = zona;
		this.suc = suc;
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

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getSuc() {
		return suc;
	}

	public void setSuc(String suc) {
		this.suc = suc;
	}

	public String getMercado() {
		return mercado;
	}

	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

}
