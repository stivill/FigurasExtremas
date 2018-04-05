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

import com.figurasextremas.model.Membresia;
import com.figurasextremas.service.IMembresiaService;




@RestController
@RequestMapping("/membresia")
public class MembresiaApi {

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IMembresiaService service;

	@RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Membresia>> getAll(){
		logger.info("> getAll ==> [Membresia]");
		
		List<Membresia> lista = null;
		try {
			lista = service.findAll();
 
			if (lista.isEmpty()) {
				lista = new ArrayList<>();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<Membresia>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("getAll <== [Membresia]");
		return new ResponseEntity<List<Membresia>>(lista, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/registrar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Membresia> createMembresia(@RequestBody Membresia memb){
		logger.info("> Create ==> [Membresia]");
		
		Membresia membresia = null;
		try {
			membresia = service.create(memb);
 
			if (membresia == null) {
				membresia = new Membresia();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Membresia>(membresia, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Create <== [Membresia]");
		return new ResponseEntity<Membresia>(membresia, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Membresia> updateMembresia(@RequestBody Membresia memb){
		logger.info("> Update ==> [Membresia]");
		
		Membresia membresia = null;
		try {
			membresia = service.update(memb);
 
			if (membresia == null) {
				membresia = new Membresia();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Membresia>(membresia, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Update <== [Membresia]");
		return new ResponseEntity<Membresia>(membresia, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> deleteMembresia(@PathVariable("id") Integer id) {
		logger.info("Delete ==> [Habilidad]");

		Integer respuesta = 0;
		
		try {
			Membresia membresia = service.find(id); 
			if(membresia == null) {
				logger.info("Membresia con id: " + id + " no existe en la base de datos.");
				return new ResponseEntity<Integer>(respuesta,HttpStatus.NOT_FOUND);
			}
			
			service.delete(id);
			respuesta = 1;
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Integer>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< Delete <== [Membresia]");
		return new ResponseEntity<Integer>(respuesta, HttpStatus.OK);
	}
	
	/* JSON TEST
	{
	    "idMembresia": 1,
	    "nombre": "Pesas Mensualidad"
	}
	*/
	
}
