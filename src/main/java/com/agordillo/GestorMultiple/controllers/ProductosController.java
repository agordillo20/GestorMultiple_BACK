package com.agordillo.GestorMultiple.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agordillo.GestorMultiple.models.Producto;
import com.agordillo.GestorMultiple.services.ProductoService;


@RestController
@RequestMapping("/productos/")
@CrossOrigin("*")
public class ProductosController {
	
	@Autowired
	private ProductoService service;
	
	@PostMapping("/create")
	public void addProduct(@RequestBody Producto prod) throws Exception {
		service.save(prod);
	}
	
	@PostMapping("/update")
	public void updateProduct(@RequestBody Producto prod) throws Exception {
		service.save(prod, prod.getId());
	}
	
	@PostMapping("/getAll")
	public List<Producto> getAllProducts() throws Exception{
		return service.getAll();
	}
	
	@PostMapping(value = "/get",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Producto getProducto(@RequestBody String id_bd) throws Exception {
		return service.get(new JSONObject(id_bd).getString("producto"));
	}
	
	

}
