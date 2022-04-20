package com.agordillo.GestorMultiple.models;

public class Linea_Carrito{
	private String cantidad;
	private Producto producto;
	
	
	public Linea_Carrito() {
		super();
	}

	public Linea_Carrito(String cantidad, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
	}

	/**
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Linea_Carrito [cantidad=" + cantidad + ", producto=" + producto + "]";
	}

	public static boolean equals(Linea_Carrito obj1,Linea_Carrito obj2) {
		return obj1.producto.getId().equals(obj2.producto.getId());
	}
	
	
	
}