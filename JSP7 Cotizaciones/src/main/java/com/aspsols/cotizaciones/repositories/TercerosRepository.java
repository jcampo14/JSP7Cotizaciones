package com.aspsols.cotizaciones.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aspsols.cotizaciones.model.Terceros;
import com.aspsols.cotizaciones.querys.TercerosQuery;

@Repository
public class TercerosRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	public List<Terceros> findByEmpresa(String empresa) {
		return jdbcTemplate.query("SELECT * FROM ("
				+ " SELECT NVL(N.C_EMP,P.C_EMP) C_EMP, NVL(N.N_IDE,P.N_IDE) N_IDE, NVL(N.NOM,P.NOMBRE) NOMBRE"
				+ " FROM PROSP_CL P" + " RIGHT JOIN (CLIENTE C JOIN NITS N ON C.N_IDE = N.N_IDE AND C.C_EMP = N.C_EMP)"
				+ " ON C.N_IDE = P.N_IDE AND C.C_EMP = P.C_EMP)" + " WHERE C_EMP = ?", new Object[] { empresa },
				new TercerosQuery());
	}
}
