package com.aspsols.cotizaciones.request;

import java.io.Serializable;

public class CotizacionDetRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cEmp;

	private String cod;	

	private Double cantidad;

	private Double precioLista;

	private Double precioVenta;

	private Integer descuento;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	

	public Double getPrecioLista() {
		return precioLista;
	}

	public void setPrecioLista(Double precioLista) {
		this.precioLista = precioLista;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Integer getDescuento() {
		return descuento;
	}

	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}

}
