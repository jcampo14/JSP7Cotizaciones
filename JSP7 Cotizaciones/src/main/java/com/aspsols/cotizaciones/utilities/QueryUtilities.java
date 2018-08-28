package com.aspsols.cotizaciones.utilities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryUtilities {
	
	/* Agregar parametros a los sql ejecutados por JdbcTemplate */
	public static void addSqlParameter(PreparedStatement ps, int index, Object value, int type) throws SQLException {
		if (value == null || value == "") {
			ps.setNull(index, type);
		} else {
			ps.setObject(index, value, type);
		}
	}

}
