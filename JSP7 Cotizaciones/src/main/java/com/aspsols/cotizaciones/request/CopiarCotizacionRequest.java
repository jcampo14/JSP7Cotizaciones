package com.aspsols.cotizaciones.request;

public class CopiarCotizacionRequest {

	private String cEmp;

	private String per;

	private String cAgr;

	private int cot;

	private int rev;

	private String usuarioElabora;

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

	public int getCot() {
		return cot;
	}

	public void setCot(int cot) {
		this.cot = cot;
	}

	public int getRev() {
		return rev;
	}

	public void setRev(int rev) {
		this.rev = rev;
	}

	public String getUsuarioElabora() {
		return usuarioElabora;
	}

	public void setUsuarioElabora(String usuarioElabora) {
		this.usuarioElabora = usuarioElabora;
	}

}
