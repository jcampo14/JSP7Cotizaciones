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
public class CreaPedidoCotizacion extends StoredProcedure {

	public static final String NAME_PROCEDURE = "CREAR_PEDIDO_COTIZACION";

	@Autowired
	public CreaPedidoCotizacion(DataSource ds) {
		super(ds, NAME_PROCEDURE);
		/* Declaramos los parametros del procedimiento */
		declareParameter(new SqlParameter("pCodEmp", Types.VARCHAR));
		declareParameter(new SqlParameter("pPer", Types.VARCHAR));
		declareParameter(new SqlParameter("pCodAgr", Types.VARCHAR));
		declareParameter(new SqlParameter("pCot", Types.INTEGER));
		declareParameter(new SqlParameter("pRev", Types.INTEGER));
		declareParameter(new SqlOutParameter("codError", Types.INTEGER));
		declareParameter(new SqlOutParameter("msgError", Types.VARCHAR));
		declareParameter(new SqlOutParameter("numeroPed", Types.INTEGER));		
		// declareParameter(new SqlParameter("idDisco", Types.INTEGER));
		// setFunction(false);
		compile();
	}

	public Map<String, Object> execute(String empresa, String periodo, String agencia, int numCot, int numRev) {		
		Map<String, Object> result;
		Map<String, Object> paramsEntrada = new HashMap<String, Object>();
		paramsEntrada.put("pCodEmp", empresa);
		paramsEntrada.put("pPer", periodo);
		paramsEntrada.put("pCodAgr", agencia);
		paramsEntrada.put("pCot", numCot);
		paramsEntrada.put("pRev", numRev);
		result = execute(paramsEntrada);		
		return result;
	}
}
