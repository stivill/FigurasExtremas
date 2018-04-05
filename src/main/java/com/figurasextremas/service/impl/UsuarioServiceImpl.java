package com.figurasextremas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.figurasextremas.model.Usuario;
import com.figurasextremas.repository.IUsuarioRepository;
import com.figurasextremas.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	
	@Autowired
	private IUsuarioRepository repo;

	@Override
	public List<Usuario> findAll() {
		return repo.findAll();
	}

	@Override
	public Usuario create(Usuario obj) {
		return repo.save(obj);
	}

	@Override
	public Usuario find(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public Usuario update(Usuario obj) {
		return repo.save(obj);
	}

	@Override
	public void delete(Integer id) {
		repo.delete(id);
		
	}

	@Override
	public Usuario getUsuarioByUsuarioAndClave(String nombre, String clave) {
		return repo.getUsuarioByUsuarioAndClave(nombre, clave);
	}
	
	
}
