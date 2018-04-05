package com.figurasextremas.service;



import com.figurasextremas.model.Cliente;

public interface IClienteService extends CRUD<Cliente>{

	Cliente getClienteByNumeroDocumento(String documento);
}
