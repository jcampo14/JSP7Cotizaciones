package com.aspsols.cotizaciones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.ServicioId;

@Entity
@Table(name = "SERVICIO")
@IdClass(ServicioId.class)
public class Servicio {

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "COD")
	private String cod;

	@Id
	@Column(name = "MON")
	private String mon;

	@Column(name = "VALOR")
	private Double pVen;

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

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	public Double getpVen() {
		return pVen;
	}

	public void setpVen(Double pVen) {
		this.pVen = pVen;
	}

}
