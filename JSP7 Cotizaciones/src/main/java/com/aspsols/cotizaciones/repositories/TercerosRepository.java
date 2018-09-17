package com.aspsols.cotizaciones.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aspsols.cotizaciones.model.Terceros;
import com.aspsols.cotizaciones.querys.TercerosQuery;
import com.aspsols.cotizaciones.utilities.QueryUtilities;

@Repository
public class TercerosRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	public List<Terceros> findByEmpresa(String empresa, String filter) {
		return jdbcTemplate.query("SELECT * FROM ("
				+ " SELECT NVL(N.C_EMP,P.C_EMP) C_EMP, NVL(N.N_IDE,P.N_IDE) N_IDE, NVL(N.NOM,P.NOMBRE) NOMBRE,"
				+ " NVL(C.IVA, P.IVA) IVA"
				+ " FROM PROSP_CL P" + " RIGHT JOIN (CLIENTE C JOIN NITS N ON C.N_IDE = N.N_IDE AND C.C_EMP = N.C_EMP)"
				+ " ON C.N_IDE = P.N_IDE AND C.C_EMP = P.C_EMP)"
				+ " WHERE C_EMP = ? AND (UPPER(NOMBRE) LIKE '%'||UPPER(?)||'%' OR UPPER(N_IDE) LIKE '%'||UPPER(?)||'%')",
				new Object[] { empresa, filter, filter }, new TercerosQuery());
	}
	
	@Transactional(readOnly = true)
	public List<Terceros> findByNit(String empresa, String nit) {
		return jdbcTemplate.query("SELECT * FROM ("
				+ " SELECT NVL(N.C_EMP,P.C_EMP) C_EMP, NVL(N.N_IDE,P.N_IDE) N_IDE, NVL(N.NOM,P.NOMBRE) NOMBRE,"
				+ " NVL(C.IVA, P.IVA) IVA"
				+ " FROM PROSP_CL P" + " RIGHT JOIN (CLIENTE C JOIN NITS N ON C.N_IDE = N.N_IDE AND C.C_EMP = N.C_EMP)"
				+ " ON C.N_IDE = P.N_IDE AND C.C_EMP = P.C_EMP)"
				+ " WHERE C_EMP = ? AND N_IDE = ?",
				new Object[] { empresa, nit }, new TercerosQuery());
	}
	
	public void insertProspecto(Terceros entity) {
		String sql = "insert into PROSP_CL(C_EMP,N_IDE,NOMBRE,ZONA)"
				+ " values(?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				//PreparedStatement ps = connection.prepareStatement(sqlEnc, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement ps = connection.prepareStatement(sql);
				QueryUtilities.addSqlParameter(ps, 1, entity.getcEmp(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 2, entity.getnIde(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 3, entity.getNombre(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 4, entity.getZona(), Types.VARCHAR);
				return ps;
			}
		});
	}
	
	public void updateProspecto(Terceros entity) {
		String sql = "UPDATE PROSP_CL SET NOMBRE = ?, ZONA = ?)"
				+ " where N_IDE = ? AND ";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				//PreparedStatement ps = connection.prepareStatement(sqlEnc, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement ps = connection.prepareStatement(sql);
				QueryUtilities.addSqlParameter(ps, 1, entity.getcEmp(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 2, entity.getnIde(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 3, entity.getNombre(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 4, entity.getZona(), Types.VARCHAR);
				return ps;
			}
		});
	}
}
