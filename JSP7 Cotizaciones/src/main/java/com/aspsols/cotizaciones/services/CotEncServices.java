package com.aspsols.cotizaciones.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.aspsols.cotizaciones.model.CotEnc;
import com.aspsols.cotizaciones.model.Vendedor;
import com.aspsols.cotizaciones.querys.CotizacionCountQuery;
import com.aspsols.cotizaciones.querys.CotizacionQuery;
import com.aspsols.cotizaciones.repositories.CotEncRepository;
import com.aspsols.cotizaciones.repositories.VendedorRepository;
import com.aspsols.cotizaciones.utilities.QueryUtilities;

@Service
public class CotEncServices {

	@Autowired
	private CotEncRepository repository;

	@Autowired
	private VendedorRepository vendedorRepository;

	public List<CotEnc> findByEmpresaAndVendedor(String empresa, String vendedor) {
		Vendedor vendedorNit = vendedorRepository.findByUsuario(empresa, vendedor);
		if (vendedorNit != null) {
			return repository.findByEmpresaAndVendedor(empresa, vendedorNit.getnIde());
		} else {
			return new ArrayList<CotEnc>();
		}
	}

	public Page<CotEnc> findByPeriod(String perIni, String perFin, String empresa, int page, int size, String order) {
		return repository.findByPeriod(perIni, perFin, empresa,
				new PageRequest(page - 1, size, QueryUtilities.oderBy(order)));
	}

	public Page<CotEnc> findByCliente(String cliente, String empresa, int page, int size, String order) {
		return repository.findByCliente(cliente, empresa,
				new PageRequest(page - 1, size, QueryUtilities.oderBy(order)));
	}

	public Page<CotEnc> findByNumero(String numeroCot, String empresa, int page, int size, String order) {
		return repository.findByNumero(numeroCot, empresa, new PageRequest(page - 1, size, QueryUtilities.oderBy(order)));
	}

	public List<CotizacionCountQuery> findByEmpresaAndVendedorGroupBy(String empresa, String vendedor) {
		Vendedor vendedorNit = vendedorRepository.findByUsuario(empresa, vendedor);
		if (vendedorNit != null) {
			return repository.findByEmpresaAndVendedorGroupBy(empresa, vendedorNit.getnIde());
		} else {
			return new ArrayList<CotizacionCountQuery>();
		}
	}

	public List<CotizacionQuery> findByCot(String empresa, String agencia, String periodo, String numeroCot) {
		return repository.findByCot(empresa, agencia, periodo, numeroCot);
	}

	public CotEnc findByCotAndRev(String empresa, String agencia, String periodo, String numeroCot, Integer revision) {
		return repository.findByCotAndRev(empresa, agencia, periodo, numeroCot, revision);
	}

}
