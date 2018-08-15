package com.aspsols.cotizaciones.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.aspsols.cotizaciones.request.CotizacionRequest;

@Repository
public class CotizacionRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public void create(CotizacionRequest record) {
		/* Insertamos el encabezado */
		String sqlEnc = "insert into TMP_COT_ENC(C_EMP,N_IDE,CRI) values(?,?,?)";		                 
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlEnc, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, record.getcEmp());
                ps.setString(2, record.getnIde());
                ps.setString(3, record.getCriVenta());
                return ps;
            }
        });
        /* Insertamos las secciones */
        String sqlSecciones = "insert into TMP_COT_SECCIONES(C_EMP,N_IDE,CRI) values(?,?,?)";
        
        /* Insertamos el detalle */
        String sqlDet = "insert into TMP_COT_DET(C_EMP,COD,CAN,LIS,VEN,DCTO) values(?,?,?,?,?,?)";		                 
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlDet, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, record.getcEmp());
                ps.setString(2, record.getnIde());
                ps.setString(3, record.getCriVenta());
                return ps;
            }
        });
	}
}
