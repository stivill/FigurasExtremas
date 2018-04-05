package com.figurasextremas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.figurasextremas.model.Membresia;
import com.figurasextremas.repository.IMembresiaRepository;
import com.figurasextremas.service.IMembresiaService;

@Service
public class MembresiaServiceImpl implements IMembresiaService {

	@Autowired
	private IMembresiaRepository repo;
	
	@Override
	public List<Membresia> findAll() {
		return repo.findAll();
	}

	@Override
	public Membresia create(Membresia obj) {
		return repo.save(obj);
	}

	@Override
	public Membresia find(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public Membresia update(Membresia obj) {
		return repo.save(obj);
	}

	@Override
	public void delete(Integer id) {
		repo.delete(id);
		
	}

}
