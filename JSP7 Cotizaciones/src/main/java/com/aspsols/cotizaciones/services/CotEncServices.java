package com.aspsols.cotizaciones.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.CotEnc;
import com.aspsols.cotizaciones.model.Vendedor;
import com.aspsols.cotizaciones.querys.CotizacionQuery;
import com.aspsols.cotizaciones.repositories.CotEncRepository;
import com.aspsols.cotizaciones.repositories.VendedorRepository;

@Service
public class CotEncServices {

	@Autowired
	private CotEncRepository repository;

	@Autowired
	private VendedorRepository vendedorRepository;

	public List<CotEnc> findByEmpresaAndVendedor(String empresa, String vendedor) {
		return repository.findByEmpresaAndVendedor(empresa, vendedor);
	}

	public List<CotizacionQuery> findByEmpresaAndVendedorGroupBy(String empresa, String vendedor) {
		Vendedor vendedorNit = vendedorRepository.findByUsuario(empresa, vendedor);
		if (vendedorNit != null) {
			return repository.findByEmpresaAndVendedorGroupBy(empresa, vendedorNit.getnIde());
		} else {
			return new ArrayList<CotizacionQuery>();
		}
	}
	
}
