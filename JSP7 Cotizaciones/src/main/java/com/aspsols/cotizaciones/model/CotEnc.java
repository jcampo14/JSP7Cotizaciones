package com.aspsols.cotizaciones.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date emi;

	@Column(name = "VEN")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ven;

	@Column(name = "PER_CT")
	private String perCt;

	@Column(name = "TOTD")
	private Double totd;

	@Column(name = "IVA")
	private Double iva;

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

	@Column(name = "DESPACHO")
	private String despacho;

	@Column(name = "TERMINO_PAGO")
	private String terminoPago;

	@Column(name = "TIEMPO_ENTREGA")
	private String tiempoEntrega;

	@Column(name = "EST")
	private String est;

	@Column(name = "LUGAR_DESTINO")
	private String lugarDestino;

	@Column(name = "OBS")
	private String obs;

	@ManyToOne
	@JoinColumn(name = "ID_CONTACTO", referencedColumnName = "ID")
	private CliContacto contacto;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "DESTINO", referencedColumnName = "C_PAI", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private Pais pais;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "COD_EMB", referencedColumnName = "COD_EMB", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private Embalaje embalaje;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "CRI", referencedColumnName = "CRI", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private Criterio criterio;

	// @ManyToOne
	// @JoinColumns({ @JoinColumn(name = "N_IDE", referencedColumnName = "N_IDE",
	// insertable = false, updatable = false),
	// @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable =
	// false, updatable = false) })
	// private Nits cliente;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "N_IDE", referencedColumnName = "N_IDE", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private FctTerceros cliente;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_VEN", referencedColumnName = "N_IDE", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private Nits vendedor;

	@OneToMany
	@OrderBy("orden ASC")
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

	public String getPerCt() {
		return perCt;
	}

	public void setPerCt(String perCt) {
		this.perCt = perCt;
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

	// public Nits getCliente() {
	// return cliente;
	// }
	//
	// public void setCliente(Nits cliente) {
	// this.cliente = cliente;
	// }

	public Nits getVendedor() {
		return vendedor;
	}

	public FctTerceros getCliente() {
		return cliente;
	}

	public void setCliente(FctTerceros cliente) {
		this.cliente = cliente;
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

	public String getDespacho() {
		return despacho;
	}

	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}

	public String getTerminoPago() {
		return terminoPago;
	}

	public void setTerminoPago(String terminoPago) {
		this.terminoPago = terminoPago;
	}

	public String getEst() {
		return est;
	}

	public void setEst(String est) {
		this.est = est;
	}

	public String getTiempoEntrega() {
		return tiempoEntrega;
	}

	public void setTiempoEntrega(String tiempoEntrega) {
		this.tiempoEntrega = tiempoEntrega;
	}

	public String getLugarDestino() {
		return lugarDestino;
	}

	public void setLugarDestino(String lugarDestino) {
		this.lugarDestino = lugarDestino;
	}

	public CliContacto getContacto() {
		return contacto;
	}

	public void setContacto(CliContacto contacto) {
		this.contacto = contacto;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

}
