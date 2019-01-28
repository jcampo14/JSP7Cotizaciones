package com.aspsols.cotizaciones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.MercadoId;

@Entity
@Table(name = "MERCADO")
@IdClass(MercadoId.class)
public class Mercado {

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "MERC")
	private String merc;

	@Column(name = "DES_MERC")
	private String desMerc;

	@Column(name = "TIPO")
	private String tipo;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getMerc() {
		return merc;
	}

	public void setMerc(String merc) {
		this.merc = merc;
	}

	public String getDesMerc() {
		return desMerc;
	}

	public void setDesMerc(String desMerc) {
		this.desMerc = desMerc;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
