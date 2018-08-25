package com.aspsols.cotizaciones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.DesctoEgrId;

@Entity
@Table(name = "DESCTO_EGR")
@IdClass(DesctoEgrId.class)
public class DesctoEgr {

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "C_DES")
	private String cDes;

	@Column(name = "N_DES")
	private String nDes;

	@Column(name = "PCTJ")
	private Double pctj;

	@Column(name = "TIPO_IMP")
	private String tipoImp;

	@Column(name = "USO_IMP")
	private String usoImp;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getcDes() {
		return cDes;
	}

	public void setcDes(String cDes) {
		this.cDes = cDes;
	}

	public String getnDes() {
		return nDes;
	}

	public void setnDes(String nDes) {
		this.nDes = nDes;
	}

	public Double getPctj() {
		return pctj;
	}

	public void setPctj(Double pctj) {
		this.pctj = pctj;
	}

	public String getTipoImp() {
		return tipoImp;
	}

	public void setTipoImp(String tipoImp) {
		this.tipoImp = tipoImp;
	}

	public String getUsoImp() {
		return usoImp;
	}

	public void setUsoImp(String usoImp) {
		this.usoImp = usoImp;
	}

}
