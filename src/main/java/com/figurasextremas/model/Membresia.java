package com.figurasextremas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "membresia")
public class Membresia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMembresia;	
	
	@Column(name = "nombre", length = 150, nullable = false)
	private String nombre;

	public Integer getIdMembresia() {
		return idMembresia;
	}

	public void setIdMembresia(Integer idMembresia) {
		this.idMembresia = idMembresia;
	}

		
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
