package com.aspsols.cotizaciones.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aspsols.cotizaciones.model.ids.CategoriaComercialId;

@Entity
@Table(name = "CATEGORIA_COMERCIAL")
@IdClass(CategoriaComercialId.class)
public class CategoriaComercial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_EMP")
	private String cEmp;

	@Id
	@Column(name = "COD_CAT")
	private String codCat;

	@Column(name = "NOMBRE_CAT")
	private String nombreCat;
	
	@Column(name = "COD_CAT_PADRE")
	private String codCatPadre;	

	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumns({
			@JoinColumn(name = "COD_CAT_PADRE", referencedColumnName = "COD_CAT", insertable = false, updatable = false),
			@JoinColumn(name = "C_EMP", referencedColumnName = "C_EMP", insertable = false, updatable = false) })
	private List<CategoriaComercial> items;

	public String getcEmp() {
		return cEmp;
	}

	public void setcEmp(String cEmp) {
		this.cEmp = cEmp;
	}

	public String getCodCat() {
		return codCat;
	}

	public void setCodCat(String codCat) {
		this.codCat = codCat;
	}

	public String getNombreCat() {
		return nombreCat;
	}

	public void setNombreCat(String nombreCat) {
		this.nombreCat = nombreCat;
	}

	public String getCodCatPadre() {
		return codCatPadre;
	}

	public void setCodCatPadre(String codCatPadre) {
		this.codCatPadre = codCatPadre;
	}	

	public List<CategoriaComercial> getItems() {
		return items;
	}

	public void setItems(List<CategoriaComercial> items) {
		this.items = items;
	}

}
