package com.figurasextremas.service;

import com.figurasextremas.model.Usuario;

public interface IUsuarioService extends CRUD<Usuario> {
	
	Usuario getUsuarioByUsuarioAndClave(String nombre, String clave);

}
