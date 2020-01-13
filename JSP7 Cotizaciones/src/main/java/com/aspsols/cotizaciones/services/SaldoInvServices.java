package com.aspsols.cotizaciones.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.db.functions.Saldo;

@Service
public class SaldoInvServices {

	@Autowired
	private Saldo saldo;
	
	public Double getSaldoInventario(String empresa, String agencia, String bodega, String codArticulo, Date fecha,
			Date hora, String lote, String periodo) {
		return saldo.executeStored(empresa, agencia, bodega, codArticulo, fecha, hora, lote, periodo);
	}
}
