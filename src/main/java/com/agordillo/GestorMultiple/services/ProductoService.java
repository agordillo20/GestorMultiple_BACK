package com.agordillo.GestorMultiple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agordillo.GestorMultiple.Common.GenericServiceImplementation;
import com.agordillo.GestorMultiple.models.Producto;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;

@Service
public class ProductoService extends GenericServiceImplementation<Producto,Producto>{

	@Autowired
	private Firestore firestore;

	@Override
	public CollectionReference getCollection() {
		return firestore.collection("productos");
	}
	
}
