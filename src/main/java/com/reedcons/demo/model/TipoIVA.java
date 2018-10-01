package com.reedcons.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TipoIVA {

	@Id
	@GeneratedValue
	private Integer id;
	private String tipo;
		
	public TipoIVA() {
	}

	public TipoIVA(Integer id, String tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
