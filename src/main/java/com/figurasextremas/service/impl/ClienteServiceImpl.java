package com.figurasextremas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.figurasextremas.model.Cliente;
import com.figurasextremas.repository.IClienteRepository;
import com.figurasextremas.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteRepository repo;
	
	
	@Override
	public List<Cliente> findAll() {
		return repo.findAll();
	}

	@Override
	public Cliente create(Cliente obj) {
		return repo.save(obj);
	}

	@Override
	public Cliente find(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public Cliente update(Cliente obj) {
		return repo.save(obj);
	}

	@Override
	public void delete(Integer id) {
		repo.delete(id);
		
	}

	@Override
	public Cliente getClienteByNumeroDocumento(String documento) {
		return repo.getClienteByNumeroDocumento(documento);
	}

	
	

}
