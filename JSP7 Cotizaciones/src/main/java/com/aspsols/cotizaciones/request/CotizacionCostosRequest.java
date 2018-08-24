package com.aspsols.cotizaciones.request;

public class CotizacionCostosRequest {

	private String cEmp;

	private String idFacCostosAdic;

	private Double valor;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getIdFacCostosAdic() {
		return idFacCostosAdic;
	}

	public void setIdFacCostosAdic(String idFacCostosAdic) {
		this.idFacCostosAdic = idFacCostosAdic;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
