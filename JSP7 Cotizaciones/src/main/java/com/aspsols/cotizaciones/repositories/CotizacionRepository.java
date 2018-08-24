package com.aspsols.cotizaciones.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.aspsols.cotizaciones.request.CotizacionCostosRequest;
import com.aspsols.cotizaciones.request.CotizacionDetRequest;
import com.aspsols.cotizaciones.request.CotizacionRequest;
import com.aspsols.cotizaciones.request.CotizacionSeccionesRequest;

@Repository
public class CotizacionRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void create(CotizacionRequest record, String idTransaccion) {
		/* Insertamos el encabezado */
		String sqlEnc = "insert into TMP_COT_ENC(ID_TRANSACCION,C_EMP,C_AGR,N_IDE,CRI,C_SUC,IDIOMA,USUARIO) values(?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sqlEnc, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, idTransaccion);
				ps.setString(2, record.getcEmp());
				ps.setString(3, record.getcAgr());
				ps.setString(4, record.getnIde());
				ps.setString(5, record.getCriVenta());
				ps.setString(6, record.getcSuc());
				ps.setString(7, record.getIdioma());
				ps.setString(8, record.getUsuario());
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
						ps.setString(1, idTransaccion);
						ps.setString(2, item.getcEmp());
						ps.setString(3, item.getCodSeccion());
						ps.setString(4, item.getDescripcionFinal());
						return ps;
					}
				});
			}
		}
		/* Insertamos el detalle */
		if (record.getDetalle().size() > 0) {
			for (CotizacionDetRequest item : record.getDetalle()) {
				String sqlDet = "insert into TMP_COT_DET(ID_TRANSACCION,C_EMP,COD,CAN,LIS,VEN,DCTO) values(?,?,?,?,?,?,?)";
				jdbcTemplate.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(sqlDet, Statement.RETURN_GENERATED_KEYS);
						ps.setString(1, idTransaccion);
						ps.setString(2, item.getcEmp());
						ps.setString(3, item.getCod());
						ps.setDouble(4, item.getCantidad());
						ps.setDouble(5, item.getPrecioLista());
						ps.setDouble(6, item.getPrecioVenta());
						ps.setDouble(7, item.getDescuento());
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
							ps.setString(1, idTransaccion);
							ps.setString(2, item.getcEmp());
							ps.setString(3, item.getIdFacCostosAdic());
							ps.setDouble(4, item.getValor());							
							return ps;
						}
					});
				}
			}
		}
	}
}
