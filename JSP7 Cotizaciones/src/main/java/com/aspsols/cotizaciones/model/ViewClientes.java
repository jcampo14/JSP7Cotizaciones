package com.aspsols.cotizaciones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT ROWNUM ID, R.C_EMP, R.N_IDE, R.NOMBRE, R.IVA, R.ZONA, R.SUC, R.MERC, R.DIR, R.TEL, R.E_MAIL,"
		+ "    R.C_PAI, P.NOM PAIS, R.DEP, D.NOMBRE DEPARTAMENTO, R.CIU, M.NOMBRE CIUDAD, CC.CARGO, CC.PER_CARGO FROM ("
		+ "    SELECT NVL(N.C_EMP,P.C_EMP) C_EMP, NVL(N.N_IDE,P.N_IDE) N_IDE, NVL(N.NOM,P.NOMBRE) NOMBRE,"
		+ "       NVL(C.IVA,P.IVA) IVA, NVL(N.ZONA,P.ZONA) ZONA, NVL(N.SUC,'N') SUC, NULL MERC, NVL(N.DIR,P.DIR) DIR,"
		+ "       NVL(N.TEL,P.TEL) TEL, NVL(N.C_PAI,P.C_PAI) C_PAI, NVL(N.DEP, P.DEP) DEP, NVL(N.CIU,P.CIU) CIU,"
		+ "       N.DIR_ELECT E_MAIL" + "    FROM CLIENTE C INNER JOIN NITS N ON C.N_IDE = N.N_IDE AND C.C_EMP = N.C_EMP"
		+ "       FULL OUTER JOIN PROSP_CL P ON C.N_IDE = P.N_IDE AND C.C_EMP = P.C_EMP ) R"
		+ "   LEFT JOIN CLI_CONTACTO CC ON CC.NIT = R.N_IDE AND CC.C_EMP = R.C_EMP"
		+ "   LEFT JOIN PAIS P ON P.C_PAI = R.C_PAI AND P.C_EMP = R.C_EMP"
		+ "   LEFT JOIN DEPARTAMENTO D ON D.C_PAI = R.C_PAI AND D.C_DPTO = R.DEP AND D.C_EMP = R.C_EMP"
		+ "   LEFT JOIN MUNICIPIO M ON M.C_MNPO = R.CIU AND M.C_DPTO = R.DEP AND M.C_PAI = R.C_PAI AND M.C_EMP = R.C_EMP")
public class ViewClientes {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "C_EMP")
	private String codEmpresa;

	@Column(name = "N_IDE")
	private String nitCliente;

	@Column(name = "NOMBRE")
	private String nombreCliente;

	@Column(name = "IVA")
	private String iva;

	@Column(name = "ZONA")
	private String zona;

	@Column(name = "SUC")
	private String sucursal;

	@Column(name = "MERC")
	private String mercado;

	@Column(name = "DIR")
	private String direccion;

	@Column(name = "TEL")
	private String telefono;

	@Column(name = "E_MAIL")
	private String email;

	@Column(name = "PAIS")
	private String pais;

	@Column(name = "DEPARTAMENTO")
	private String departamento;

	@Column(name = "CIUDAD")
	private String ciudad;

	@Column(name = "CARGO")
	private String contactoCargo;

	@Column(name = "PER_CARGO")
	private String contactoPersona;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getNitCliente() {
		return nitCliente;
	}

	public void setNitCliente(String nitCliente) {
		this.nitCliente = nitCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getMercado() {
		return mercado;
	}

	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getContactoCargo() {
		return contactoCargo;
	}

	public void setContactoCargo(String contactoCargo) {
		this.contactoCargo = contactoCargo;
	}

	public String getContactoPersona() {
		return contactoPersona;
	}

	public void setContactoPersona(String contactoPersona) {
		this.contactoPersona = contactoPersona;
	}

}
