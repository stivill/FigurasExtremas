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

import com.figurasextremas.model.Pago;
import com.figurasextremas.service.IPagoService;


@RestController
@RequestMapping("/pago")
public class PagoApi {
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IPagoService service;

	@RequestMapping(value = "/listar/{documento}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pago>> getPagosByDocumentoCliente(@PathVariable("documento") String documento){
		logger.info("> Pagos  ==> [Cliente]");
		
		List<Pago> lista = null;
		
		try {
			lista = service.getPagosByDocumentoCliente(documento);
 
			if (lista.isEmpty() ) {
				lista = new ArrayList<>();
				logger.info("Lista Vacia");
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<Pago>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Pagos <== [Cliente]");
		return new ResponseEntity<List<Pago>>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pago>> getAll(){
		logger.info("> getAll ==> [Pago]");
		
		List<Pago> lista = null;
		try {
			lista = service.findAll();
 
			if (lista.isEmpty()) {
				lista = new ArrayList<>();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<Pago>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("getAll <== [Pago]");
		return new ResponseEntity<List<Pago>>(lista, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/listar/{tipoRango}/{fechaInicio}/{fechaFin}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Pago>> getPagosByFechas(@PathVariable("tipoRango") String tipoRango, @PathVariable("fechaInicio") String fechaInicio, @PathVariable("fechaFin") String fechaFin){
		logger.info("> Pagos  ==> [Fechas]");
		
		List<Pago> lista = null;
		
		try {
			
			if (tipoRango.equals("U")) {
				lista = service.getPagosByFechaUnica(fechaInicio, fechaFin);
			}
			
			if (tipoRango.equals("M")) {
				lista = service.findByFechaFinBetween(fechaInicio, fechaFin);
			}
			
			
 
			if (lista.isEmpty() ) {
				lista = new ArrayList<>();
				logger.info("Lista Vacia");
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<Pago>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Pagos <== [Cliente]");
		return new ResponseEntity<List<Pago>>(lista, HttpStatus.OK);
	}
	
	
	
	
	
	
	@RequestMapping(value = "/registrar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Pago> createPago(@RequestBody Pago pag){
		logger.info("> Create ==> [Pago]");
		
		Pago pago = null;
		try {
			pago = service.create(pag);
 
			if (pago == null) {
				pago = new Pago();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Pago>(pago, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Create <== [Pago]");
		return new ResponseEntity<Pago>(pago, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Pago> updatePago(@RequestBody Pago pag){
		logger.info("> Update ==> [Pago]");
		
		Pago pago = null;
		try {
			pago = service.update(pag);
 
			if (pago == null) {
				pago = new Pago();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Pago>(pago, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Update <== [Pago]");
		return new ResponseEntity<Pago>(pag, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> deletePago(@PathVariable("id") Integer id) {
		logger.info("Delete ==> [Pago]");

		Integer respuesta = 0;
		
		try {
			Pago pago = service.find(id); 
			
			if(pago == null) {
				logger.info("Pago con id: " + id + " no existe en la base de datos.");
				return new ResponseEntity<Integer>(respuesta,HttpStatus.NOT_FOUND);
			}
			
			service.delete(id);
			respuesta = 1;
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Integer>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< Delete <== [Pago]");
		return new ResponseEntity<Integer>(respuesta, HttpStatus.OK);
	}
	
	/* JSON TEST
	{
    
   
    "idPago": "",
    "membresia": {
        "idMembresia" :1
    },
    "cliente": {
        "idCliente" : 1
    },
    "fechaInicio": "2018-03-02",
    "fechaFin": "2018-04-02",
    "valor": 100000
    
    
}*/

}
