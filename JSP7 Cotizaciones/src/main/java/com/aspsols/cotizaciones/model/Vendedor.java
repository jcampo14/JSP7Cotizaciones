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

import com.aspsols.cotizaciones.model.ids.VendedorId;

@Entity
@Table(name = "VENDEDOR")
@IdClass(VendedorId.class)
public class Vendedor implements Serializable {

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

	@Column(name = "USUARIO_SP6")
	private String usuario;

	@ManyToOne
	@JoinColumns(value = { @JoinColumn(name = "N_IDE", referencedColumnName = "N_IDE", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private Nits nit;

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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Nits getNit() {
		return nit;
	}

	public void setNit(Nits nit) {
		this.nit = nit;
	}

}
