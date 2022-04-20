package com.agordillo.GestorMultiple.models;

public class Roles {
	private String nombre_rol;
	private String userId;
	
	public Roles() {
		
	}
	
	public Roles(String nombre_rol, String userId) {
		super();
		this.nombre_rol = nombre_rol;
		this.userId = userId;
	}


	public String getNombre_rol() {
		return nombre_rol;
	}


	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
