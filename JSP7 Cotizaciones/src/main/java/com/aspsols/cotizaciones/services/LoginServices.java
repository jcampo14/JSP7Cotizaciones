package com.aspsols.cotizaciones.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.querys.CursorWorkingSchedule;
import com.aspsols.cotizaciones.responses.ProcessResponse;
import com.aspsolutions.framework.database.SQLParameter;
import com.aspsolutions.jdbc.ConnectionManager;
import com.aspsolutions.jdbc.NamedQueryExecutor;
import com.aspsolutions.jdbc.NamedQueryIterator;
import com.aspsolutions.jdbc.Record;
import com.aspsolutions.jdbc.SQL;
import com.aspsolutions.jforms.commons.StringEncrypter;
import com.aspsolutions.jforms.commons.StringEncrypter.EncryptionException;
import com.aspsolutions.jforms.commons.exceptions.NoDataFoundException;

@Service
public class LoginServices {
			
	public ProcessResponse login(String usuario, String clave, String empresa){
		ProcessResponse response = new ProcessResponse();
		SQL sql = ConnectionManager.stablishDatabaseConnection();
		try {
			sql = ConnectionManager.stablishDatabaseConnection();
			String selectSql = "SELECT U.USERNAME USUARIO, U.ACTIVO, U.NOMBRE, E.C_EMP EMPRESA, U.PASSWORD CLAVE, U.CODIGO"
					+ " FROM USUARIOS U" + " JOIN OPP$USU O ON O.OPP$NOM = U.USERNAME"
					+ " LEFT JOIN USU_AGE E ON E.USUARIO = U.USERNAME" + " WHERE U.USERNAME = :USUARIO";
			List<SQLParameter> argsSelectSql = new ArrayList<SQLParameter>();
			argsSelectSql.add(new SQLParameter("USUARIO", usuario));
			Record recordSelectSql = sql.executeSelectInto(selectSql, argsSelectSql);
			/* Validamos que la clave sea correcta */
			if (validarClaveDelUsuario(clave, recordSelectSql.getString("CLAVE"))) {
				/* Validamos que el usuario tenga acceso a la empresa seleccionada */
				if (empresa.equals(recordSelectSql.getString("EMPRESA"))) {
					/* Validamos que este en el horario donde pueda trabajar */
					if (isWorkingSchedule(recordSelectSql.getString("CODIGO"),sql)) {//						
						response.setSuccess(true);
						response.setMessage(recordSelectSql.getString("NOMBRE"));												
					} else {
						response.setSuccess(false);
						response.setMessage("Usuario no tiene permisos para trabajar en el horario actual");						
					}
				} else {
					response.setSuccess(false);
					response.setMessage("Usuario no asignado a la empresa seleccionada");					
				}
			} else {
				response.setSuccess(false);
				response.setMessage("Usuario y/o Contraseña invalidos");				
			}
		} catch (NoDataFoundException e) {
			response.setSuccess(false);
			response.setMessage("El usuario no existe");					
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("El usuario se encuentra duplicado");			
		} finally {
			sql.closeDatabaseConnection();
		}
		return response;
	}
	
	private boolean validarClaveDelUsuario(String typedPassword, String truePassword) {
		StringEncrypter encrypter;
		try {
			encrypter = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DEFAULT_ENCRYPTION_KEY);
			String encryptedPassword = encrypter.encrypt(typedPassword);
			if (truePassword.equals(encryptedPassword)) {
				return true;
			}
		} catch (EncryptionException e) {
			e.printStackTrace();
		}

		return false;
	}

	private boolean isWorkingSchedule(String codigo, SQL sql) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date date = calendar.getTime();
		String hour = new SimpleDateFormat("HH").format(date);
		List<SQLParameter> arguments = new ArrayList<SQLParameter>();

		arguments.add(new SQLParameter("usuario", codigo));
		arguments.add(new SQLParameter("dia", DayOfWeek.getDayOfWeekName(calendar.get(Calendar.DAY_OF_WEEK))));
		NamedQueryExecutor<CursorWorkingSchedule> namedQryExec = new NamedQueryExecutor<CursorWorkingSchedule>(
				CursorWorkingSchedule.class, sql);
		NamedQueryIterator<CursorWorkingSchedule> cursorIteratorForC = namedQryExec.executeNamedQuery(arguments);
		if (cursorIteratorForC != null) {
			try {
				while (cursorIteratorForC.hasNext()) {
					CursorWorkingSchedule cursorRecord = cursorIteratorForC.populateNext(new CursorWorkingSchedule());
					String workingHour = cursorRecord.getHora();
					if (workingHour != null && workingHour.equals(hour)) {
						return true;
					}
				}
			} finally {
				cursorIteratorForC.close();
			}
		}
		return false;
	}

	enum DayOfWeek {

		MONDAY(1, "LUN"), TUESDAY(2, "MAR"), WEDNESDAY(3, "MIE"), THURSDAY(4, "JUE"), FRYDAY(5, "VIE"), SATURDAYDAY(6,
				"SAB"), SUNDAY(7, "DOM"), SUNDAY2(0, "DOM");

		int code;
		String description;

		DayOfWeek(int code, String description) {
			this.code = code;
			this.description = description;
		}

		public static String getDayOfWeekName(int code) {
			for (DayOfWeek dayOfWeek : values()) {
				if (dayOfWeek.code == code) {
					return dayOfWeek.description;
				}
			}
			return null;
		}
	}
}
