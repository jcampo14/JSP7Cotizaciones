package com.aspsols.cotizaciones.querys;

public class CotizacionCountQuery {

	private String cEmp;

	private String per;

	private String cAgr;

	private String cot;

	private Long numeroRev;

	public CotizacionCountQuery(String cEmp, String per, String cAgr, String cot, Long numeroRev) {
		super();
		this.cEmp = cEmp;
		this.per = per;
		this.cAgr = cAgr;
		this.cot = cot;
		this.numeroRev = numeroRev;
	}

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

	public Long getNumeroRev() {
		return numeroRev;
	}

	public void setNumeroRev(Long numeroRev) {
		this.numeroRev = numeroRev;
	}

}
