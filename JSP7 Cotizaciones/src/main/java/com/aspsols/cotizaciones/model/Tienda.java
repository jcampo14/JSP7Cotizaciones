package com.aspsols.cotizaciones.model;

import java.io.Serializable;

public class Tienda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String estado;
	private Integer precio;
	private String url;
	
	public Tienda(String nombre, String estado, Integer precio, String url) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		this.precio = precio;
		this.url = url;
		
	}
	
	public Tienda() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	
	
}
