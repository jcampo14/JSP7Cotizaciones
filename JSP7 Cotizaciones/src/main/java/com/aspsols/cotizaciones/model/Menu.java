package com.aspsols.cotizaciones.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MENU_PORTAL_WEB")
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "OPCION")
	private String label;

	@Column(name = "URL")
	private String link;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_PADRE", referencedColumnName = "ID")
	private Menu idPadre;
	
	@OneToMany(mappedBy = "idPadre", orphanRemoval = true, cascade = CascadeType.REMOVE)
	private List<Menu> items;

	public Menu() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Menu getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Menu idPadre) {
		this.idPadre = idPadre;
	}

	public List<Menu> getItems() {
		return items;
	}

	public void setItems(List<Menu> items) {
		this.items = items;
	}

}
