package com.aspsols.cotizaciones.db.procedures;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

@Service
public class CreaCotizacion extends StoredProcedure {

	public static final String NAME_PROCEDURE = "CREAR_COTIZACION";

	@Autowired
	public CreaCotizacion(DataSource ds) {
		super(ds, NAME_PROCEDURE);
		/* Declaramos los parametros del procedimiento */
		declareParameter(new SqlParameter("idTransaccion", Types.VARCHAR));
		declareParameter(new SqlOutParameter("codError", Types.INTEGER));
		declareParameter(new SqlOutParameter("msgError", Types.VARCHAR));
		// declareParameter(new SqlParameter("idDisco", Types.INTEGER));
		// setFunction(false);
		compile();
	}

	public Map<String, Object> execute(String idTransaccion) {		
		Map<String, Object> result;
		Map<String, Object> paramsEntrada = new HashMap<String, Object>();
		paramsEntrada.put("idTransaccion", idTransaccion);
		result = execute(paramsEntrada);		
		return result;
	}

}
