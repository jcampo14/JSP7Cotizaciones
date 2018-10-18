package com.aspsols.cotizaciones.utilities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.data.domain.Sort;

public class QueryUtilities {

	/* Agregar parametros a los sql ejecutados por JdbcTemplate */
	public static void addSqlParameter(PreparedStatement ps, int index, Object value, int type) throws SQLException {
		if (value == null || value == "") {
			ps.setNull(index, type);
		} else {
			ps.setObject(index, value, type);
		}
	}

	/* Crear un orderBy */
	public static Sort oderBy(String field) {
		if (field.contains("-")) {
			return new Sort(Sort.Direction.DESC, field.replace("-", ""));
		} else {
			return new Sort(Sort.Direction.ASC, field);
		}
	}

}
