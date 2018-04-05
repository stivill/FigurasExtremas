package com.figurasextremas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.figurasextremas.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Query("from Usuario u where u.usuario = :usuario and u.clave= :clave")
	Usuario getUsuarioByUsuarioAndClave(@Param("usuario") String nombre, @Param("clave") String clave);

}
