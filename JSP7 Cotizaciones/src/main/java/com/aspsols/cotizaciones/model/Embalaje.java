package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.EmbalajeId;
import com.aspsols.cotizaciones.model.ids.IncotermId;

@Entity(name = "Embalaje")
@Table(name = "Embalaje")
@IdClass(EmbalajeId.class)
public class Embalaje implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "COD_EMB")
	private String cEmb;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "VERSION")
	private Integer version;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getcEmb() {
		return cEmb;
	}

	public void setcEmb(String cEmb) {
		this.cEmb = cEmb;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
