package com.aspsols.cotizaciones.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.CotEncId;

@Entity
@Table(name = "COT_ENC")
@IdClass(CotEncId.class)
public class CotEnc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "PER")
	private String per;

	@Id
	@Column(name = "C_AGR")
	private String cAgr;

	@Id
	@Column(name = "COT")
	private String cot;

	@Id
	@Column(name = "REV")
	private Integer rev;

	@Column(name = "EMI")
	private Date emi;

	@Column(name = "TOTD")
	private Double totd;

	@Column(name = "N_IDE")
	private String nIde;

	@Column(name = "C_VEN")
	private String cVen;

	@Column(name = "COD_SUC")
	private String codSuc;

	@Column(name = "IDIOMA")
	private String idioma;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false),
			@JoinColumn(name = "COD_EMB", referencedColumnName = "COD_EMB", insertable = false, updatable = false) })
	private Embalaje embajale;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "CRI", referencedColumnName = "CRI", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private Criterio criterio;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false),
			@JoinColumn(name = "N_IDE", referencedColumnName = "N_IDE", insertable = false, updatable = false) })
	private Nits cliente;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false),
			@JoinColumn(name = "C_VEN", referencedColumnName = "N_IDE", insertable = false, updatable = false) })
	private Nits vendedor;

	@OneToMany
	@JoinColumns({ @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false),
			@JoinColumn(name = "PER", referencedColumnName = "PER", insertable = false, updatable = false),
			@JoinColumn(name = "C_AGR", referencedColumnName = "C_AGR", insertable = false, updatable = false),
			@JoinColumn(name = "COT", referencedColumnName = "COT", insertable = false, updatable = false),
			@JoinColumn(name = "REV", referencedColumnName = "REV", insertable = false, updatable = false) })
	private List<CotDet> detalle;

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

	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	public Date getEmi() {
		return emi;
	}

	public void setEmi(Date emi) {
		this.emi = emi;
	}

	public Double getTotd() {
		return totd;
	}

	public void setTotd(Double totd) {
		this.totd = totd;
	}

	public String getnIde() {
		return nIde;
	}

	public void setnIde(String nIde) {
		this.nIde = nIde;
	}

	public String getcVen() {
		return cVen;
	}

	public void setcVen(String cVen) {
		this.cVen = cVen;
	}

	public String getCodSuc() {
		return codSuc;
	}

	public void setCodSuc(String codSuc) {
		this.codSuc = codSuc;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Embalaje getEmbajale() {
		return embajale;
	}

	public void setEmbajale(Embalaje embajale) {
		this.embajale = embajale;
	}

	public Criterio getCriterio() {
		return criterio;
	}

	public void setCriterio(Criterio criterio) {
		this.criterio = criterio;
	}

	public Nits getCliente() {
		return cliente;
	}

	public void setCliente(Nits cliente) {
		this.cliente = cliente;
	}

	public Nits getVendedor() {
		return vendedor;
	}

	public void setVendedor(Nits vendedor) {
		this.vendedor = vendedor;
	}

	public List<CotDet> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<CotDet> detalle) {
		this.detalle = detalle;
	}

}
