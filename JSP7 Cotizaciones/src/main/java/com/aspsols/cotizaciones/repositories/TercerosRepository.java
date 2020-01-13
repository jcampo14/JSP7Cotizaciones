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
		return jdbcTemplate.query("SELECT * FROM ( "
				+ " SELECT NVL(N.C_EMP,P.C_EMP) C_EMP, NVL(N.N_IDE,P.N_IDE) N_IDE, NVL(N.NOM,P.NOMBRE) NOMBRE,"
				+ "    NVL(C.IVA,P.IVA) IVA, NVL(N.ZONA,P.ZONA) ZONA, NVL(N.SUC,'N') SUC, NULL MERC, N.DIR, N.TEL,"
				+ "    N.C_PAI, N.DEP, N.CIU" + " FROM CLIENTE C"
				+ "    INNER JOIN NITS N ON C.N_IDE = N.N_IDE AND C.C_EMP = N.C_EMP"
				+ "    FULL OUTER JOIN PROSP_CL P ON C.N_IDE = P.N_IDE AND C.C_EMP = P.C_EMP)"
				+ "    WHERE C_EMP = ? AND (UPPER(NOMBRE) LIKE '%'||UPPER(?)||'%' OR UPPER(N_IDE) LIKE '%'||UPPER(?)||'%')",
				new Object[] { empresa, filter, filter }, new TercerosQuery());
	}

	@Transactional(readOnly = true)
	public List<Terceros> findByNit(String empresa, String nit) {
		return jdbcTemplate.query(
				"SELECT * FROM ( SELECT NVL(N.C_EMP,P.C_EMP) C_EMP, NVL(N.N_IDE,P.N_IDE) N_IDE, NVL(N.NOM,P.NOMBRE) NOMBRE,"
						+ " NVL(C.IVA,P.IVA) IVA, NVL(N.ZONA,P.ZONA) ZONA, NVL(N.SUC,'N') SUC, NULL MERC, N.DIR, N.TEL,"
						+ " N.C_PAI, N.DEP, N.CIU" + " FROM CLIENTE C"
						+ " INNER JOIN NITS N ON C.N_IDE = N.N_IDE AND C.C_EMP = N.C_EMP"
						+ " FULL OUTER JOIN PROSP_CL P ON C.N_IDE = P.N_IDE AND C.C_EMP = P.C_EMP )"
						+ " WHERE C_EMP = ? AND N_IDE = ?",
				new Object[] { empresa, nit }, new TercerosQuery());
	}

	@Transactional(readOnly = true)
	public List<Terceros> findByProspecto(String empresa) {
		return jdbcTemplate.query("SELECT * FROM ("
				+ " SELECT P.C_EMP C_EMP, P.N_IDE N_IDE, P.NOMBRE NOMBRE, P.IVA IVA, P.ZONA, 'N' SUC, P.MERC MERC,"
				+ " P.DIR, P.TEL, P.C_PAI, P.DEP, P.CIU" + " FROM PROSP_CL P)" + " WHERE C_EMP = ?",
				new Object[] { empresa }, new TercerosQuery());
	}

	public void insertProspecto(Terceros entity) {
		String sql = "insert into PROSP_CL(C_EMP,N_IDE,NOMBRE,ZONA,IVA,MERC,DIR,TEL,C_PAI,DEP,CIU)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				// PreparedStatement ps = connection.prepareStatement(sqlEnc,
				// Statement.RETURN_GENERATED_KEYS);
				PreparedStatement ps = connection.prepareStatement(sql);
				QueryUtilities.addSqlParameter(ps, 1, entity.getcEmp(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 2, entity.getnIde(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 3, entity.getNombre(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 4, entity.getZona(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 5, entity.getIva(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 6, entity.getMercado(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 7, entity.getDir(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 8, entity.getTel(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 9, entity.getcPai(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 10, entity.getDep(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 11, entity.getCiu(), Types.VARCHAR);
				return ps;
			}
		});
	}

	public void updateProspecto(Terceros entity) {
		String sql = "UPDATE PROSP_CL SET NOMBRE = ?, ZONA = ?, IVA = ?, MERC = ?, DIR = ?, TEL = ?, C_PAI = ?, DEP = ?,"
				+ " CIU = ? WHERE N_IDE = ? AND C_EMP = ?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				// PreparedStatement ps = connection.prepareStatement(sqlEnc,
				// Statement.RETURN_GENERATED_KEYS);
				PreparedStatement ps = connection.prepareStatement(sql);
				QueryUtilities.addSqlParameter(ps, 1, entity.getNombre(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 2, entity.getZona(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 3, entity.getIva(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 4, entity.getMercado(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 5, entity.getDir(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 6, entity.getTel(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 7, entity.getcPai(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 8, entity.getDep(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 9, entity.getCiu(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 10, entity.getnIde(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 11, entity.getcEmp(), Types.VARCHAR);
				return ps;
			}
		});
	}

	public void deleteProspecto(Terceros entity) {
		String sql = "DELETE FROM PROSP_CL" + " WHERE N_IDE = ? AND C_EMP = ?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				// PreparedStatement ps = connection.prepareStatement(sqlEnc,
				// Statement.RETURN_GENERATED_KEYS);
				PreparedStatement ps = connection.prepareStatement(sql);
				QueryUtilities.addSqlParameter(ps, 1, entity.getnIde(), Types.VARCHAR);
				QueryUtilities.addSqlParameter(ps, 2, entity.getcEmp(), Types.VARCHAR);
				return ps;
			}
		});
	}
}
