package com.aspsols.cotizaciones.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.aspsols.cotizaciones.request.CotizacionCostosRequest;
import com.aspsols.cotizaciones.request.CotizacionDetRequest;
import com.aspsols.cotizaciones.request.CotizacionRequest;
import com.aspsols.cotizaciones.request.CotizacionSeccionesRequest;
import com.aspsols.cotizaciones.utilities.QueryUtilities;

@Repository
public class CotizacionRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void create(CotizacionRequest record, String idTransaccion) {
		/* Insertamos el encabezado */
		String sqlEnc = "insert into TMP_COT_ENC(ID_TRANSACCION,C_EMP,C_AGR,N_IDE,CRI,C_SUC,IDIOMA,USUARIO,DIAS_VALIDEZ) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sqlEnc, Statement.RETURN_GENERATED_KEYS);
				QueryUtilities.addSqlParameter(ps, 1, idTransaccion, Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 2, record.getcEmp(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 3, record.getcAgr(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 4, record.getnIde(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 5, record.getCriVenta(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 6, record.getcSuc(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 7, record.getIdioma(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 8, record.getUsuario(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 9, record.getDiasValidez(), Types.VARCHAR);
				return ps;
			}
		});
		/* Insertamos las secciones */
		if (record.getSecciones().size() > 0) {
			for (CotizacionSeccionesRequest item : record.getSecciones()) {
				String sqlSecciones = "insert into TMP_COT_SECCIONES(ID_TRANSACCION,C_EMP,COD_SECCION,DESCRIPCION_FINAL) values(?,?,?,?)";
				jdbcTemplate.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(sqlSecciones,
								Statement.RETURN_GENERATED_KEYS);
						QueryUtilities.addSqlParameter(ps, 1, idTransaccion, Types.VARCHAR);
						QueryUtilities.addSqlParameter(ps, 2, item.getcEmp(), Types.VARCHAR);
						QueryUtilities.addSqlParameter(ps, 3, item.getCodSeccion(), Types.VARCHAR);
						QueryUtilities.addSqlParameter(ps, 4, item.getDescripcionFinal(), Types.VARCHAR);
						return ps;
					}
				});
			}
		}
		/* Insertamos el detalle */
		if (record.getDetalle().size() > 0) {
			for (CotizacionDetRequest item : record.getDetalle()) {
				String sqlDet = "insert into TMP_COT_DET(ID_TRANSACCION,C_EMP,COD,CAN,LIS,VEN,DCTO,COD_IVA) values(?,?,?,?,?,?,?,?)";
				jdbcTemplate.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(sqlDet, Statement.RETURN_GENERATED_KEYS);
						QueryUtilities.addSqlParameter(ps, 1, idTransaccion, Types.VARCHAR);
						QueryUtilities.addSqlParameter(ps, 2, item.getcEmp(), Types.VARCHAR);
						QueryUtilities.addSqlParameter(ps, 3, item.getCod(), Types.VARCHAR);
						QueryUtilities.addSqlParameter(ps, 4, item.getCantidad(), Types.DOUBLE);
						QueryUtilities.addSqlParameter(ps, 5, item.getPrecioLista(), Types.DOUBLE);
						QueryUtilities.addSqlParameter(ps, 6, item.getPrecioVenta(), Types.DOUBLE);
						QueryUtilities.addSqlParameter(ps, 7, item.getDescuento(), Types.DOUBLE);
						QueryUtilities.addSqlParameter(ps, 8, item.getCodIva(), Types.VARCHAR);
						return ps;
					}
				});
			}
		}
		/* Insertamos los costos */
		if (record.getCostos().size() > 0) {
			for (CotizacionCostosRequest item : record.getCostos()) {
				if (item.getValor() != 0) {
					String sqlDet = "insert into TMP_COT_COSTOS(ID_TRANSACCION,C_EMP,COD_COSTO,VALOR) values(?,?,?,?)";
					jdbcTemplate.update(new PreparedStatementCreator() {
						@Override
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement ps = connection.prepareStatement(sqlDet, Statement.RETURN_GENERATED_KEYS);
							QueryUtilities.addSqlParameter(ps, 1, idTransaccion, Types.VARCHAR);
							QueryUtilities.addSqlParameter(ps, 2, item.getcEmp(), Types.VARCHAR);
							QueryUtilities.addSqlParameter(ps, 3, item.getIdFacCostosAdic(), Types.VARCHAR);
							QueryUtilities.addSqlParameter(ps, 4, item.getValor(), Types.VARCHAR);
							return ps;
						}
					});
				}
			}
		}
	}

}