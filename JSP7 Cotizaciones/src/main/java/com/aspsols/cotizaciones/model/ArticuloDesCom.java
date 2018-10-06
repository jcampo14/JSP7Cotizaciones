package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ARTICULO_DESC_COM")
public class ArticuloDesCom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICULO_DESC_COM_ID_SEQ")
	@SequenceGenerator(name = "ARTICULO_DESC_COM_ID_SEQ", sequenceName = "ARTICULO_DESC_COM_ID_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "ID")	
	private Long id;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "COD")
	private String cod;

	@Column(name = "IDIOMA")
	private String idioma;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
