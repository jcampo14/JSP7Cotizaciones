package com.aspsols.cotizaciones.request;

import java.io.Serializable;
import java.util.List;

public class CotizacionRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cEmp;

	private String nIde;

	private String criVenta;

	private List<CotizacionDetRequest> detalle;

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

	public String getCriVenta() {
		return criVenta;
	}

	public void setCriVenta(String criVenta) {
		this.criVenta = criVenta;
	}

	public List<CotizacionDetRequest> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<CotizacionDetRequest> detalle) {
		this.detalle = detalle;
	}

}
