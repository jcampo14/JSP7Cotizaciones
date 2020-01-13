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
	private String dir;
	private String tel;
	private String cPai;
	private String dep;
	private String ciu;

	public Terceros(String cEmp, String nIde, String nombre, String iva, String zona, String suc, String mercado,
			String dir, String tel, String cPai, String dep, String ciu) {
		super();
		this.cEmp = cEmp;
		this.nIde = nIde;
		this.nombre = nombre;
		this.iva = iva;
		this.zona = zona;
		this.suc = suc;
		this.mercado = mercado;
		this.dir = dir;
		this.tel = tel;
		this.cPai = cPai;
		this.dep = dep;
		this.ciu = ciu;
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

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getcPai() {
		return cPai;
	}

	public void setcPai(String cPai) {
		this.cPai = cPai;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getCiu() {
		return ciu;
	}

	public void setCiu(String ciu) {
		this.ciu = ciu;
	}

}
