package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.IncotermFacCostosAdicId;

@Entity
@Table(name = "INCOTERM_FAC_COSTOS_ADIC")
@IdClass(IncotermFacCostosAdicId.class)
public class IncotermFacCostosAdic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	
	@Id
	@Column(name = "ID_INCOTERM")
	private String idIncoterm;

	
	@Id
	@Column(name = "ID_FAC_COSTOS_ADIC")
	private String idFacCostosAdic;

	
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false),
			@JoinColumn(name = "ID_INCOTERM", referencedColumnName = "COD_INCOTERM", insertable = false, updatable = false) })
	private Incoterm incoterm;

	
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false),
			@JoinColumn(name = "ID_FAC_COSTOS_ADIC", referencedColumnName = "CODIGO", insertable = false, updatable = false) })
	private FacCostosAdic facCostosAdic;
		
	public IncotermFacCostosAdic() {
		super();
	}
		
	public IncotermFacCostosAdic(String cEmp, String idIncoterm, String idFacCostosAdic) {
		super();
		this.cEmp = cEmp;
		this.idIncoterm = idIncoterm;
		this.idFacCostosAdic = idFacCostosAdic;
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getIdIncoterm() {
		return idIncoterm;
	}

	public void setIdIncoterm(String idIncoterm) {
		this.idIncoterm = idIncoterm;
	}

	public String getIdFacCostosAdic() {
		return idFacCostosAdic;
	}

	public void setIdFacCostosAdic(String idFacCostosAdic) {
		this.idFacCostosAdic = idFacCostosAdic;
	}

	public Incoterm getIncoterm() {
		return incoterm;
	}

	public void setIncoterm(Incoterm incoterm) {
		this.incoterm = incoterm;
	}

	public FacCostosAdic getFacCostosAdic() {
		return facCostosAdic;
	}

	public void setFacCostosAdic(FacCostosAdic facCostosAdic) {
		this.facCostosAdic = facCostosAdic;
	}
	

}
