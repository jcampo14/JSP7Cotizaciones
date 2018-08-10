package com.aspsols.cotizaciones.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuarios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "CODIGO")
	private String codigo;	

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ACTIVO")
	private String activo;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD_DATE")
	private Date passwordDate;

	public Usuarios(String codigo, String username, String password, String activo, String nombre, String email,
			Date passwordDate) {
		super();
		this.codigo = codigo;
		this.username = username;
		this.password = password;
		this.activo = activo;
		this.nombre = nombre;
		this.email = email;
		this.passwordDate = passwordDate;
	}

	public Usuarios() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getPasswordDate() {
		return passwordDate;
	}

	public void setPasswordDate(Date passwordDate) {
		this.passwordDate = passwordDate;
	}

}
