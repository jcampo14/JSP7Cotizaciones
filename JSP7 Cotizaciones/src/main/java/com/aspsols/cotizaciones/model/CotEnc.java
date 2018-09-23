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
import com.aspsols.cotizaciones.utilities.CustomDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@Column(name = "EMI")
	private Date emi;

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@Column(name = "VEN")
	private Date ven;

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

	@Column(name = "COD_INCOTERM")
	private String codIncoterm;

	@Column(name = "ORIGEN")
	private String origen;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false),
			@JoinColumn(name = "DESTINO", referencedColumnName = "C_PAI", insertable = false, updatable = false) })
	private Pais pais;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false),
			@JoinColumn(name = "COD_EMB", referencedColumnName = "COD_EMB", insertable = false, updatable = false) })
	private Embalaje embalaje;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "CRI", referencedColumnName = "CRI", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private Criterio criterio;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "N_IDE", referencedColumnName = "N_IDE", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private Nits cliente;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_VEN", referencedColumnName = "N_IDE", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private Nits vendedor;

	@OneToMany
	@JoinColumns({ @JoinColumn(name = "COT", referencedColumnName = "COT", insertable = false, updatable = false),
			@JoinColumn(name = "REV", referencedColumnName = "REV", insertable = false, updatable = false),
			@JoinColumn(name = "C_AGR", referencedColumnName = "C_AGR", insertable = false, updatable = false),
			@JoinColumn(name = "PER", referencedColumnName = "PER", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private List<CotDet> detalle;

	@OneToMany
	@JoinColumns({ @JoinColumn(name = "COT", referencedColumnName = "COT", insertable = false, updatable = false),
			@JoinColumn(name = "REV", referencedColumnName = "REV", insertable = false, updatable = false),
			@JoinColumn(name = "C_AGR", referencedColumnName = "C_AGR", insertable = false, updatable = false),
			@JoinColumn(name = "PER", referencedColumnName = "PER", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private List<CotEncSecciones> secciones;

	@OneToMany
	@JoinColumns({ @JoinColumn(name = "NUM_DOC", referencedColumnName = "COT", insertable = false, updatable = false),
			@JoinColumn(name = "REV", referencedColumnName = "REV", insertable = false, updatable = false),
			@JoinColumn(name = "C_AGR", referencedColumnName = "C_AGR", insertable = false, updatable = false),
			@JoinColumn(name = "PER", referencedColumnName = "PER", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private List<FacCostosCot> cargos;

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

	public Date getVen() {
		return ven;
	}

	public void setVen(Date ven) {
		this.ven = ven;
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

	public Embalaje getEmbalaje() {
		return embalaje;
	}

	public void setEmbalaje(Embalaje embalaje) {
		this.embalaje = embalaje;
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

	public String getCodIncoterm() {
		return codIncoterm;
	}

	public void setCodIncoterm(String codIncoterm) {
		this.codIncoterm = codIncoterm;
	}

	public List<CotEncSecciones> getSecciones() {
		return secciones;
	}

	public void setSecciones(List<CotEncSecciones> secciones) {
		this.secciones = secciones;
	}

	public List<FacCostosCot> getCargos() {
		return cargos;
	}

	public void setCargos(List<FacCostosCot> cargos) {
		this.cargos = cargos;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
