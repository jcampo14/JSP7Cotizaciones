package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "PROSP_CL")
@IdClass(ProspectoClienteId.class)
public class ProspectoCliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "N_IDE")
	private String nIde;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "EST")
	private String est;

	public ProspectoCliente() {
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

	public String getEst() {
		return est;
	}

	public void setEst(String est) {
		this.est = est;
	}

}
