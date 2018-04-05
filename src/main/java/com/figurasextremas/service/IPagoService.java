package com.figurasextremas.service;

import java.util.List;


import com.figurasextremas.model.Pago;

public interface IPagoService extends CRUD<Pago> {
	
	List<Pago> getPagosByDocumentoCliente(String documento);
	
	List<Pago> getPagosByFechaUnica(String fechaInicio, String fechaFin);
	
	//List<Pago> getPagosByFechaRango(String fechaInicio, String fechaFin);
	
	List<Pago> findByFechaFinBetween(String fechaInicio, String fechaFin);
	

}
