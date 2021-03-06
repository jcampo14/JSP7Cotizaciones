package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aspsols.cotizaciones.model.Tienda;
import com.aspsols.cotizaciones.querys.TiendaQuery;

@Repository
public class TiendaRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly = true)
	public List<Tienda> QueryProducts(String c_emp, String rama, String imgPrincipal){
		return jdbcTemplate.query("SELECT ar.nom as nombre, p.p_ven as precio, ar.cod as codigo, im.image_url as URL"
				+ " FROM ARTICULO ar inner join PRECIO p on ar.c_emp = p.c_emp AND ar.cod = p.cod"
				+ " left join IMAGENES_ARTICULO im on ar.c_emp = im.c_emp AND ar.cod = im.codigo_articulo AND im.principal = ?"
				+ " WHERE ar.c_emp = ? AND ar.rama = ?", new Object[] {imgPrincipal , c_emp, rama}, new TiendaQuery());
	}
}
