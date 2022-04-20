package com.agordillo.GestorMultiple.models;

import java.util.List;

public class Producto {

	private String id;
	private String name;
	private Double price;
	private String description;
	private String photoURL;
	private List<String> specialFeatures;
	private Double range;
	private List<String> category;
	private String brand;
	private List<String> size;
	
	
	
	public Producto(String name, Double price, String description, String photoURL, List<String> category,
			String brand) {
		this(name,price, description, photoURL, null, null, category, brand, null);
	}
	
	
	public Producto() {
		super();
	}


	public Producto(String name, Double price, String description, String photoURL, List<String> specialFeatures,
			Double range, List<String> category, String brand, List<String> size) {
		this(null,name,price,description,photoURL,specialFeatures,range,category,brand,size);
	}
	
	public Producto(String id,String name, Double price, String description, String photoURL, List<String> specialFeatures,
			Double range, List<String> category, String brand, List<String> size) {
		super();
		this.setId(id);
		this.name = name;
		this.price = price;
		this.description = description;
		this.photoURL = photoURL;
		this.specialFeatures = specialFeatures;
		this.range = range;
		this.category = category;
		this.brand = brand;
		this.size = size;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the price
	 */
	public Number getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the photoURL
	 */
	public String getPhotoURL() {
		return photoURL;
	}
	/**
	 * @param photoURL the photoURL to set
	 */
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	/**
	 * @return the specialFeatures
	 */
	public List<String> getSpecialFeatures() {
		return specialFeatures;
	}
	/**
	 * @param specialFeatures the specialFeatures to set
	 */
	public void setSpecialFeatures(List<String> specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	/**
	 * @return the range
	 */
	public Number getRange() {
		return range;
	}
	/**
	 * @param range the range to set
	 */
	public void setRange(Double range) {
		this.range = range;
	}
	/**
	 * @return the category
	 */
	public List<String> getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(List<String> category) {
		this.category = category;
	}
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return the size
	 */
	public List<String> getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(List<String> size) {
		this.size = size;
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
