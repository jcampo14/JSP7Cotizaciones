package com.aspsols.cotizaciones.querys;

import java.util.Date;

import com.aspsols.cotizaciones.model.FctTerceros;

public class CotizacionQuery {

	private String cEmp;

	private String per;

	private String cAgr;

	private String cot;

	private int numeroRev;

	private Date emi;

	// private Nits cliente;

	private FctTerceros cliente;

	public CotizacionQuery(String cEmp, String per, String cAgr, String cot, int numeroRev, Date emi,
			FctTerceros cliente) {
		super();
		this.cEmp = cEmp;
		this.per = per;
		this.cAgr = cAgr;
		this.cot = cot;
		this.numeroRev = numeroRev;
		this.emi = emi;
		this.cliente = cliente;
	}

	// public CotizacionQuery(String cEmp, String per, String cAgr, String cot, int
	// numeroRev, Date emi, Nits cliente) {
	// super();
	// this.cEmp = cEmp;
	// this.per = per;
	// this.cAgr = cAgr;
	// this.cot = cot;
	// this.numeroRev = numeroRev;
	// this.emi = emi;
	// this.cliente = cliente;
	// }

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	public String getcAgr() {
		return cAgr;
	}

	public void setcAgr(String cAgr) {
		this.cAgr = cAgr;
	}

	public String getCot() {
		return cot;
	}

	public void setCot(String cot) {
		this.cot = cot;
	}

	public int getNumeroRev() {
		return numeroRev;
	}

	public void setNumeroRev(int numeroRev) {
		this.numeroRev = numeroRev;
	}

	public Date getEmi() {
		return emi;
	}

	public void setEmi(Date emi) {
		this.emi = emi;
	}

	public FctTerceros getCliente() {
		return cliente;
	}

	public void setCliente(FctTerceros cliente) {
		this.cliente = cliente;
	}

	// public Nits getCliente() {
	// return cliente;
	// }
	//
	// public void setCliente(Nits cliente) {
	// this.cliente = cliente;
	// }

}
