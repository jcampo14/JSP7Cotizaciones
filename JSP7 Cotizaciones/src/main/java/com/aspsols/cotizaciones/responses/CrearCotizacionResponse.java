package com.aspsols.cotizaciones.responses;

public class CrearCotizacionResponse {

	private boolean success;

	private String message;

	private String perCot;

	private int numCot;

	private int numRev;

	public CrearCotizacionResponse() {
		super();
	}

	public CrearCotizacionResponse(boolean success, String message, String perCot, int numCot, int numRev) {
		super();
		this.success = success;
		this.message = message;
		this.perCot = perCot;
		this.numCot = numCot;
		this.numRev = numRev;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPerCot() {
		return perCot;
	}

	public void setPerCot(String perCot) {
		this.perCot = perCot;
	}

	public int getNumCot() {
		return numCot;
	}

	public void setNumCot(int numCot) {
		this.numCot = numCot;
	}

	public int getNumRev() {
		return numRev;
	}

	public void setNumRev(int numRev) {
		this.numRev = numRev;
	}

}
