package com.aspsols.cotizaciones.utilities;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateNoTimeSerializer extends StdSerializer<Date> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public CustomDateNoTimeSerializer() {
		this(null);
	}

	@SuppressWarnings("unchecked")
	public CustomDateNoTimeSerializer(@SuppressWarnings("rawtypes") Class t) {
		super(t);
	}

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		gen.writeString(formatter.format(value));
	}
}
