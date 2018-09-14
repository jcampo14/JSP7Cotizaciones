package com.aspsols.cotizaciones.querys;

public class CotizacionQuery {

	private String cEmp;

	private String per;

	private String cAgr;

	private String cot;

	public CotizacionQuery(String cEmp, String per, String cAgr, String cot) {
		super();
		this.cEmp = cEmp;
		this.per = per;
		this.cAgr = cAgr;
		this.cot = cot;
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

}
