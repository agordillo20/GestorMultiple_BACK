package com.agordillo.GestorMultiple.models;

import java.util.List;

public class Carrito {


	//es posible que haya que añadir la fecha para la busqueda y filtrado de los carritos
	private String id;
	
	private String user;
	private boolean active;
	private List<Linea_Carrito> lineas_productos;
	
	
	 
	public Carrito() {
		super();
	}

	public Carrito(String id,String user, List<Linea_Carrito> lineas_productos,boolean active) {
		this.user = user;
		this.id = id;
		this.lineas_productos = lineas_productos;
		this.active = active;
	}
	
	public Carrito(String user, List<Linea_Carrito> lineas, boolean active) {
		this(null,user,lineas,active);
	}

	@Override
	public String toString() {
		return "Carrito [id=" + id + ", user=" + user + ", productos=" + lineas_productos.toString() + ", active=" + active + "]";
	}
	
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the lineas_productos
	 */
	public List<Linea_Carrito> getLineas_productos() {
		return lineas_productos;
	}

	/**
	 * @param lineas_productos the lineas_productos to set
	 */
	public void setLineas_productos(List<Linea_Carrito> lineas_productos) {
		this.lineas_productos = lineas_productos;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
