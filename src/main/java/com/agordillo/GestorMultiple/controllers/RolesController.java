package com.agordillo.GestorMultiple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agordillo.GestorMultiple.models.Roles;
import com.agordillo.GestorMultiple.services.RolesService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/roles/")
@CrossOrigin("*")
public class RolesController{
	
	@Autowired
	private RolesService service;
	
	@PostMapping("/get")
	public Roles GetUserRole(@RequestBody String userId) throws Exception {
		return service.get(userId);
	}
	
	@PostMapping(value = "/addToRole",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addUserToRole(@RequestBody ObjectNode node) throws Exception {
		JsonNode id_bd = node.get("id_bd");
		JsonNode nodej = node.get("rol");
		Roles rol = new JsonMapper().treeToValue(nodej,Roles.class);
		if(id_bd==null) {	
			return service.save(rol);
		}else {
			return service.save(rol, id_bd.asText());
		}
	}

}
