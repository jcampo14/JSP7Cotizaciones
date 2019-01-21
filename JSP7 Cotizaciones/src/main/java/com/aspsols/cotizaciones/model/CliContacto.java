package com.aspsols.cotizaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CLI_CONTACTO")
public class CliContacto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "CLI_CONTACTO_SEQ")
	@Column(name = "ID")
	private Long id;

	@Column(name = "C_EMP")
	private String cEmp;

	@Column(name = "NIT")
	private String nit;

	@Column(name = "CARGO")
	private String cargo;

	@Column(name = "PER_CARGO")
	private String perCargo;

	/*
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false),
			@JoinColumn(name = "NIT", referencedColumnName = "N_IDE", insertable = false, updatable = false) })
	private Nits personaNit;
	*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getPerCargo() {
		return perCargo;
	}

	public void setPerCargo(String perCargo) {
		this.perCargo = perCargo;
	}
/*
	public Nits getPersonaNit() {
		return personaNit;
	}

	public void setPersonaNit(Nits personaNit) {
		this.personaNit = personaNit;
	}
*/
}
