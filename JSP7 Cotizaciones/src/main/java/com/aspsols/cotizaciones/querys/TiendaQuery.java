package com.aspsols.cotizaciones.querys;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.aspsols.cotizaciones.model.Tienda;

public class TiendaQuery implements RowMapper<Tienda>{

	@Override
	public Tienda mapRow(ResultSet arg0, int arg1) throws SQLException{
		Tienda tienda = new Tienda();
		tienda.setCodigo(arg0.getString("codigo"));
		tienda.setNombre(arg0.getString("nombre"));
		tienda.setPrecio(arg0.getInt("precio"));
		tienda.setUrl(arg0.getString("URL"));
		
		return tienda;
	}
}
