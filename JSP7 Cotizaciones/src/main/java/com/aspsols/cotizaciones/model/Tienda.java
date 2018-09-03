package com.aspsols.cotizaciones.model;

import java.io.Serializable;

public class Tienda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String nombre;
	private Integer precio;
	private String url;
	
	public Tienda(String nombre, Integer precio, String url, String codigo) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	
	
}
