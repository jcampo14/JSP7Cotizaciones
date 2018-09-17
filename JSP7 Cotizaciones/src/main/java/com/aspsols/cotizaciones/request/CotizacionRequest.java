package com.aspsols.cotizaciones.request;

import java.io.Serializable;
import java.util.List;

public class CotizacionRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cEmp;

	private String cAgr;

	private String nIde;

	private String criVenta;

	private String cSuc;

	private String idioma;

	private String usuario;

	private Integer diasValidez;

	private String embalaje;

	private String iva;

	private String cot;

	private Integer rev;

	private String incoterm;

	private String modificar;

	private List<CotizacionSeccionesRequest> secciones;

	private List<CotizacionDetRequest> detalle;

	private List<CotizacionCostosRequest> costos;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getcAgr() {
		return cAgr;
	}

	public void setcAgr(String cAgr) {
		this.cAgr = cAgr;
	}

	public String getnIde() {
		return nIde;
	}

	public void setnIde(String nIde) {
		this.nIde = nIde;
	}

	public String getCriVenta() {
		return criVenta;
	}

	public void setCriVenta(String criVenta) {
		this.criVenta = criVenta;
	}

	public String getcSuc() {
		return cSuc;
	}

	public void setcSuc(String cSuc) {
		this.cSuc = cSuc;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public List<CotizacionDetRequest> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<CotizacionDetRequest> detalle) {
		this.detalle = detalle;
	}

	public List<CotizacionSeccionesRequest> getSecciones() {
		return secciones;
	}

	public void setSecciones(List<CotizacionSeccionesRequest> secciones) {
		this.secciones = secciones;
	}

	public List<CotizacionCostosRequest> getCostos() {
		return costos;
	}

	public void setCostos(List<CotizacionCostosRequest> costos) {
		this.costos = costos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getDiasValidez() {
		return diasValidez;
	}

	public void setDiasValidez(Integer diasValidez) {
		this.diasValidez = diasValidez;
	}

	public String getEmbalaje() {
		return embalaje;
	}

	public void setEmbalaje(String embalaje) {
		this.embalaje = embalaje;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getCot() {
		return cot;
	}

	public void setCot(String cot) {
		this.cot = cot;
	}

	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	public String getIncoterm() {
		return incoterm;
	}

	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}

	public String getModificar() {
		return modificar;
	}

	public void setModificar(String modificar) {
		this.modificar = modificar;
	}

}
