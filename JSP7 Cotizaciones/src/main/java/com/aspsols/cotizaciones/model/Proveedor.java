package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.ProveedorId;

@Entity
@Table(name = "PROVEEDOR")
@IdClass(ProveedorId.class)
public class Proveedor implements Serializable{

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

	@Column(name = "N_CHE")
	private String nChe;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEm) {
		this.cEmp = cEm;
	}

	public String getnIde() {
		return nIde;
	}

	public void setnIde(String nIde) {
		this.nIde = nIde;
	}

	public String getnChe() {
		return nChe;
	}

	public void setnChe(String nChe) {
		this.nChe = nChe;
	}

}
