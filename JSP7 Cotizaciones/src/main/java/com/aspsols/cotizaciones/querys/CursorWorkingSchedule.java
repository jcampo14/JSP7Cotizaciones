package com.aspsols.cotizaciones.querys;

import com.aspsolutions.jdbc.annotations.ColumnResult;
import com.aspsolutions.jdbc.annotations.NamedQuery;


@NamedQuery(query="SELECT OPP$COD USUARIO,DIA,TO_CHAR(HI,'HH24') HORA "+
				  " FROM OPP$USU_HOR "+
				  " WHERE OPP$COD=:USUARIO AND DIA=:DIA "+
				  " ORDER BY HI")
public class CursorWorkingSchedule {


	
	@ColumnResult(column="USUARIO")
	private String usuario;
	
	@ColumnResult(column="DIA")
	private String dia;
	
	@ColumnResult(column="HORA")
	private String hora;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	
}
