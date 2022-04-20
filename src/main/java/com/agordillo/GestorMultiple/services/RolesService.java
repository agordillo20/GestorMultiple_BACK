package com.agordillo.GestorMultiple.services;
import com.agordillo.GestorMultiple.models.Roles;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agordillo.GestorMultiple.Common.GenericServiceImplementation;

@Service
public class RolesService extends GenericServiceImplementation<Roles,Roles>{

	@Autowired
    private Firestore firestore;

	@Override
	public CollectionReference getCollection() {
		return firestore.collection("roles");
	}

}
