package com.figurasextremas.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.figurasextremas.model.Usuario;
import com.figurasextremas.service.IUsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioApi {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUsuarioService service;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getUsuarioByUsuarioAndClave(@RequestBody Usuario usu){
		logger.info("> Login  ==> [Usuario]");
		
		Usuario usuario = null;
		Integer respuesta = 0;
		try {
			usuario = service.getUsuarioByUsuarioAndClave(usu.getUsuario(), usu.getClave());
 
			if (usuario != null) {
				respuesta = 1;
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Integer>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Login <== [Usuario]");
		return new ResponseEntity<Integer>(respuesta, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Usuario>> getAll(){
		logger.info("> getAll ==> [Usuario]");
		
		List<Usuario> lista = null;
		try {
			lista = service.findAll();
 
			if (lista.isEmpty()) {
				lista = new ArrayList<>();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<Usuario>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("getAll <== [Usuario]");
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/listar/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Integer id){
		logger.info("getByDocumento ==> [Usuario]");
		
		Usuario usuario = null;
		try {
			usuario = service.find(id);
 
			if (usuario == null) {
				usuario = new Usuario();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("getByDocumento <== [Cliente]");
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/registrar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usu){
		logger.info("> Create ==> [Usuario]");
		
		Usuario usuario = null;
		try {
			usuario = service.create(usu);
 
			if (usuario == null) {
				usuario = new Usuario();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Create <== [Cliente]");
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usu){
		logger.info("> Update ==> [Usuario]");
		
		Usuario usuario = null;
		try {
			usuario = service.update(usu);
 
			if (usuario == null) {
				usuario = new Usuario();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Update <== [Usuario]");
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> deleteUsuario(@PathVariable("id") Integer id) {
		logger.info("Delete ==> [Usuario]");

		Integer respuesta = 0;
		
		try {
			Usuario usuario = service.find(id); 
			
			if(usuario == null) {
				logger.info("Usuario con id: " + id + " no existe en la base de datos.");
				return new ResponseEntity<Integer>(respuesta,HttpStatus.NOT_FOUND);
			}
			
			service.delete(id);
			respuesta = 1;
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Integer>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< Delete <== [Usuario]");
		return new ResponseEntity<Integer>(respuesta, HttpStatus.OK);
	}
	
	/* JSON TEST
	{
    "idUsuario": 4,
    "usuario": "stivill",
    "clave": "123",
    "estado": "1"
	}
	*/

}
