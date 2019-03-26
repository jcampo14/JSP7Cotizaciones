package com.aspsols.cotizaciones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.FacCostosAdicId;

@Entity
@Table(name = "FAC_COSTOS_ADIC")
@IdClass(FacCostosAdicId.class)
public class FacCostosAdic {

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "CODIGO")
	private String codigo;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "MONEDA")
	private String moneda;

	@Column(name = "C_CTA")
	private String cCta;

	@Column(name = "C_RET")
	private String cRet;

	@Column(name = "C_IVA")
	private String cIva;

	@Column(name = "VERSION")
	private Integer version;

	@Column(name = "TIPO")
	private String tipo;

	public FacCostosAdic() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getcCta() {
		return cCta;
	}

	public void setcCta(String cCta) {
		this.cCta = cCta;
	}

	public String getcRet() {
		return cRet;
	}

	public void setcRet(String cRet) {
		this.cRet = cRet;
	}

	public String getcIva() {
		return cIva;
	}

	public void setcIva(String cIva) {
		this.cIva = cIva;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
