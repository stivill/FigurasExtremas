package com.figurasextremas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.figurasextremas.model.Pago;
import com.figurasextremas.repository.IPagoRepository;
import com.figurasextremas.service.IPagoService;


@Service
public class PagoServiceImpl implements IPagoService {

	@Autowired
	private IPagoRepository repo;
	
	@Override
	public List<Pago> findAll() {
		return repo.findAll();
	}

	@Override
	public Pago create(Pago obj) {
		return repo.save(obj);
	}

	@Override
	public Pago find(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public Pago update(Pago obj) {
		return repo.save(obj);
	}

	@Override
	public void delete(Integer id) {
		repo.delete(id);
		
	}

	@Override
	public List<Pago> getPagosByDocumentoCliente(String documento) {
		return repo.getPagosByDocumentoCliente(documento);
	}

	@Override
	public List<Pago> getPagosByFechaUnica(String fechaInicio, String fechaFin) {
		return repo.getPagosByFechaUnica(fechaInicio, fechaFin);
	}

	@Override
	public List<Pago> findByFechaFinBetween(String fechaInicio, String fechaFin) {
		return repo.findByFechaFinBetween(fechaInicio, fechaFin);
	}

	

}
