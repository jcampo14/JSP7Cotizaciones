package com.aspsols.cotizaciones.querys;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.aspsols.cotizaciones.model.Terceros;

public class TercerosQuery implements RowMapper<Terceros> {

	@Override
	public Terceros mapRow(ResultSet arg0, int arg1) throws SQLException {		
		Terceros row = new Terceros();
		row.setcEmp(arg0.getString("C_EMP"));
		row.setnIde(arg0.getString("N_IDE"));
		row.setNombre(arg0.getString("NOMBRE"));
		row.setIva(arg0.getString("IVA"));
		row.setZona(arg0.getString("ZONA"));
		return row;
	}

}
