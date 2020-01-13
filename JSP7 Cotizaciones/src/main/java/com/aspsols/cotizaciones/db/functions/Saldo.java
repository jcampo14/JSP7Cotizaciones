package com.aspsols.cotizaciones.db.functions;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

@Service
public class Saldo extends StoredProcedure {

	public static final String NAME_PROCEDURE = "SALDO";

	@Autowired
	public Saldo(DataSource ds) {
		super(ds, NAME_PROCEDURE);
		/* Declaramos los parametros del procedimiento */
		declareParameter(new SqlOutParameter("saldoInv", Types.DOUBLE));
		declareParameter(new SqlParameter("pCodEmp", Types.VARCHAR));
		declareParameter(new SqlParameter("pCodAgr", Types.VARCHAR));
		declareParameter(new SqlParameter("pBod", Types.VARCHAR));
		declareParameter(new SqlParameter("pCod", Types.VARCHAR));
		declareParameter(new SqlParameter("pFecha", Types.DATE));
		declareParameter(new SqlParameter("pHora", Types.DATE));
		declareParameter(new SqlParameter("pLote", Types.VARCHAR));
		declareParameter(new SqlParameter("pPeriodo", Types.VARCHAR));
		setFunction(true);
		compile();
	}

	public Double executeStored(String empresa, String agencia, String bodega, String codArticulo, Date fecha,
			Date hora, String lote, String periodo) {
		Map<String, Object> result;
		Map<String, Object> paramsEntrada = new HashMap<String, Object>();
		paramsEntrada.put("pCodEmp", empresa);
		paramsEntrada.put("pCodAgr", agencia);
		paramsEntrada.put("pBod", bodega);
		paramsEntrada.put("pCod", codArticulo);
		paramsEntrada.put("pFecha", fecha);
		paramsEntrada.put("pHora", hora);
		paramsEntrada.put("pLote", lote);
		paramsEntrada.put("pPeriodo", periodo);
		result = execute(paramsEntrada);
		return (Double) result.get("saldoInv");
	}

}
