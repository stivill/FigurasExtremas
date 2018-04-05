package com.figurasextremas.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.figurasextremas.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Query("from Cliente c where c.numeroDocumento = :documento")
	Cliente getClienteByNumeroDocumento(@Param("documento") String documento);

}
