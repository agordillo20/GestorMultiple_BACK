package com.agordillo.GestorMultiple.controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.agordillo.GestorMultiple.models.Carrito;
import com.agordillo.GestorMultiple.models.Linea_Carrito;
import com.agordillo.GestorMultiple.services.CarritoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

@RestController
@RequestMapping("/carrito/")
@CrossOrigin("*")
public class CarritoController {

	@Autowired
	private CarritoService service;

	@PostMapping(value = "/set", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String setCarrito(@RequestBody ObjectNode nodo) throws Exception {
		JsonNode padre = nodo.get("carrito");
		JsonNode active = padre.get("active");
		JsonNode id = padre.get("id");
		JsonNode user = padre.get("user");
		ArrayNode productos = (ArrayNode) padre.get("lineas_productos");
		var lineas = getCarrito_Internal(user.textValue());
		if(lineas==null) {
			lineas = new ArrayList<>();
		}
		
		if (productos.isArray()) {
			for (final JsonNode node : productos) {
				var linea = new ObjectMapper().convertValue(node, Linea_Carrito.class);
				int indice = -1;
				for(int i=0;i<lineas.size();i++) {
					if(Linea_Carrito.equals(linea,lineas.get(i))) {
						indice = i;
						break;
					}
				}
				System.out.println("indice =>"+indice);
				if(indice != -1) {
					lineas.set(indice,linea);
				}else {
					lineas.add(linea);
				}
			}
		}else {
			throw new Exception("Producto no recibido como array, error controlado.");
		}
			
		
		
		String id_resultado;
		if (id != null) {
			var carrito = new Carrito(id.textValue(),user.textValue(), lineas,active.asBoolean());
			id_resultado =  service.save(carrito, carrito.getId());
		} else {
			var carrito_old = this.getCarritoByUserId(user.textValue());
			if(carrito_old!=null) {
				var carrito = new Carrito(carrito_old.getId(),user.textValue(), lineas,active.asBoolean());
				id_resultado =  service.save(carrito, carrito.getId());
			}else {
				var carrito = new Carrito(user.textValue(), lineas,active.asBoolean());
				id_resultado =  service.save(carrito);
			}
			
		}
		System.out.println(id_resultado);
		return "{\"id_resultado\":\""+id_resultado+"\"}";
	}

	@PostMapping("/get")
	public List<Linea_Carrito> getCarrito(@RequestBody String UserId) throws Exception {
		Carrito carrito = service.getCarritoByUserId(new JSONObject(UserId).getString("UserId"));
		return carrito!=null?carrito.getLineas_productos():null;
	}
	
	private List<Linea_Carrito> getCarrito_Internal(String UserId) throws Exception{
		Carrito carrito = service.getCarritoByUserId(UserId);
		return carrito!=null?carrito.getLineas_productos():null;
	}
	
	private Carrito getCarritoByUserId(String userId) throws Exception {
		return service.getCarritoByUserId(userId);
	}
	
	
	@PostMapping(value = "/deleteLine")
	public void deleteLineaOfCart(@RequestBody String mensaje) throws Exception {
		var object = new JSONObject(mensaje).getJSONObject("mensaje");
		var userId = object.getString("userId");
		var jslinea = object.get("linea");
		var linea = new Gson().fromJson(jslinea.toString(),Linea_Carrito.class);
		var carrito = getCarritoByUserId(userId);
		service.deleteLineaCarrito(carrito, linea);
	}

}
