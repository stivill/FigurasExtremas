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

import com.figurasextremas.model.Cliente;
import com.figurasextremas.service.IClienteService;


@RestController
@RequestMapping("/cliente")
public class ClienteApi {

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IClienteService service;

	@RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> getAll(){
		logger.info("> getAll ==> [Clientes]");
		
		List<Cliente> lista = null;
		try {
			lista = service.findAll();
 
			if (lista.isEmpty()) {
				lista = new ArrayList<>();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<Cliente>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("getAll <== [Clientes]");
		return new ResponseEntity<List<Cliente>>(lista, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/listar/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id){
		logger.info("getById ==> [Cliente]");
		
		Cliente cliente = null;
		try {
			cliente = service.find(id);
 
			if (cliente == null) {
				cliente = new Cliente();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("getById <== [Cliente]");
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/leer/{documento}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> getClienteByNumeroDocumento(@PathVariable("documento") String documento){
		logger.info("getByDocumento ==> [Cliente]");
		
		Cliente cliente = null;
		try {
			cliente = service.getClienteByNumeroDocumento(documento);
 
			if (cliente == null) {
				cliente = new Cliente();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("getByDocumento <== [Cliente]");
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/registrar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Cliente> createCliente(@RequestBody Cliente cli){
		logger.info("> Create ==> [Cliente]");
		
		Cliente cliente = null;
		try {
			cliente = service.create(cli);
 
			if (cliente == null) {
				cliente = new Cliente();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Create <== [Cliente]");
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cli){
		logger.info("> Update ==> [Cliente]");
		
		Cliente cliente = null;
		try {
			cliente = service.update(cli);
 
			if (cliente == null) {
				cliente = new Cliente();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Update <== [Cliente]");
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> deleteCliente(@PathVariable("id") Integer id) {
		logger.info("Delete ==> [Cliente]");

		Integer respuesta = 0;
		
		try {
			Cliente cliente = service.find(id); 
			if(cliente == null) {
				logger.info("Cliente con id: " + id + " no existe en la base de datos.");
				return new ResponseEntity<Integer>(respuesta,HttpStatus.NOT_FOUND);
			}
			
			service.delete(id);
			respuesta = 1;
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Integer>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< Delete <== [Cliente]");
		return new ResponseEntity<Integer>(respuesta, HttpStatus.OK);
	}
	
	/* JSON TEST
	{
    "idCliente": 1,
    "numeroDocumento": "1063136030",
    "nombres": "Stivill",
    "apellidos": "Mendoza Rivera",
    "telefono": "3044497482",
    "direccion": "Cll 4 # 16-20 Centro",
    "correo": "stivillrivera@gmail.com"
}
	*/
}
