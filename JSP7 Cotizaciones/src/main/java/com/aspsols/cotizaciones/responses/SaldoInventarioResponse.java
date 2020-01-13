package com.aspsols.cotizaciones.responses;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SaldoInventarioResponse {

	private String codEmpresa;
	private String codAgencia;
	private String codBodega;
	private String codArticulo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fecha;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date hora;
	private String lote;
	private String periodo;
	private Double saldoInv;

	public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getCodAgencia() {
		return codAgencia;
	}

	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}

	public String getCodBodega() {
		return codBodega;
	}

	public void setCodBodega(String codBodega) {
		this.codBodega = codBodega;
	}

	public String getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(String codArticulo) {
		this.codArticulo = codArticulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Double getSaldoInv() {
		return saldoInv;
	}

	public void setSaldoInv(Double saldoInv) {
		this.saldoInv = saldoInv;
	}

}
