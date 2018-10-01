package com.reedcons.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cliente {

	@Id
	private int dni;
	private String nombre;
	private String cuit;

	@OneToMany(mappedBy="cliente")
	private List<Direccion> direcciones;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="tipoIVAId")
	private TipoIVA tipoIVA;

	public Cliente(int dni, String nombre, String cuit, List<Direccion> direcciones, TipoIVA tipoIVA) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.cuit = cuit;
		this.direcciones = direcciones;
		this.tipoIVA = tipoIVA;
	}
	
	public Cliente() {		
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public List<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public TipoIVA getTipoIVA() {
		return tipoIVA;
	}

	public void setTipoIVA(TipoIVA tipoIVA) {
		this.tipoIVA = tipoIVA;
	}

}
