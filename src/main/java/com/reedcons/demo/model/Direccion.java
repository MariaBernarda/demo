package com.reedcons.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Direccion {

	@Id
	@GeneratedValue
	private Integer id;
	private String calle;
	private String nro;

	@ManyToOne
	@JoinColumn(name="idCliente",nullable=true)
	private Cliente cliente;
	
	public Direccion(Integer id, String calle, String nro) {
		super();
		this.id = id;
		this.calle = calle;
		this.nro = nro;
	}
	
	public Direccion() {
	}
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	

}
