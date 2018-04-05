package com.figurasextremas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.figurasextremas.model.Pago;

public interface IPagoRepository extends JpaRepository<Pago, Integer> {

	@Query("select new com.figurasextremas.model.Pago(p.id, p.membresia, p.fechaInicio, p.fechaFin, p.valor) from Pago p where p.cliente.numeroDocumento = :documento")
	List<Pago> getPagosByDocumentoCliente(@Param("documento") String documento);
	
	@Query("from Pago p where p.fechaFin >= :fechaInicio and p.fechaFin < :fechaFin")
	List<Pago> getPagosByFechaUnica(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
	
	//@Query("from Pago p where p.fechaFin >= :fechaInicio and p.fechaFin <= :fechaFin")
	List<Pago> findByFechaFinBetween(String fechaInicio, String fechaFin);
}
