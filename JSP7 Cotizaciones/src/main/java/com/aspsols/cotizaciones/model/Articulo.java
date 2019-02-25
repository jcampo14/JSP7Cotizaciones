package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.ArticuloId;

@Entity
@Table(name = "ARTICULO")
@IdClass(ArticuloId.class)
public class Articulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "COD")
	private String cod;

	@Column(name = "NOM")
	private String nom;

	@Column(name = "UNI")
	private String uni;

	@Column(name = "NOM_LARGO")
	private String nomLargo;

	@Column(name = "RAMA")
	private String rama;

	@Column(name = "FACT")
	private String fact;

	@Column(name = "IVA")
	private String iva;

	@Column(name = "CLASE")
	private String clase;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false),
			@JoinColumn(name = "IVA", referencedColumnName = "C_DES", insertable = false, updatable = false) })
	private DesctoEgr idIva;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false),
			@JoinColumn(name = "CLASE", referencedColumnName = "CLASE", insertable = false, updatable = false) })
	private Clase idClase;

	public Articulo() {
		super();
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

	public String getUni() {
		return uni;
	}

	public void setUni(String uni) {
		this.uni = uni;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomLargo() {
		return nomLargo;
	}

	public void setNomLargo(String nomLargo) {
		this.nomLargo = nomLargo;
	}

	public String getRama() {
		return rama;
	}

	public void setRama(String rama) {
		this.rama = rama;
	}

	public String getFact() {
		return fact;
	}

	public void setFact(String fact) {
		this.fact = fact;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public DesctoEgr getIdIva() {
		return idIva;
	}

	public void setIdIva(DesctoEgr idIva) {
		this.idIva = idIva;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public Clase getIdClase() {
		return idClase;
	}

	public void setIdClase(Clase idClase) {
		this.idClase = idClase;
	}

}
