package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.jsonviewer.JSonServiceViewer;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "INCOTERM")
@IdClass(IncotermId.class)
public class Incoterm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@JsonView(JSonServiceViewer.Public.class)
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@JsonView(JSonServiceViewer.Public.class)
	@Column(name = "COD_INCOTERM")
	private String codIncoterm;

	@JsonView(JSonServiceViewer.Public.class)
	@Column(name = "NOMBRE")
	private String nombre;

	@JsonView(JSonServiceViewer.Private.class)
	@Column(name = "VERSION")
	private Integer version;
	

	public Incoterm() {
		super();
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getCodIncoterm() {
		return codIncoterm;
	}

	public void setCodIncoterm(String codIncoterm) {
		this.codIncoterm = codIncoterm;
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

	// public List<IncotermFacCostosAdic> getListFacCostosAdic() {
	// return listFacCostosAdic;
	// }
	//
	// public void setListFacCostosAdic(List<IncotermFacCostosAdic>
	// listFacCostosAdic) {
	// this.listFacCostosAdic = listFacCostosAdic;
	// }

}
