package com.agordillo.GestorMultiple.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agordillo.GestorMultiple.Common.GenericServiceImplementation;
import com.agordillo.GestorMultiple.models.User;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;

@Service
public class UserService extends GenericServiceImplementation<User,User>{

    @Autowired
    private Firestore firestore;
    
    @Autowired
    private AuthUsersService auth;
    
	@Override
	public CollectionReference getCollection() {
		return firestore.collection("users");
	}

	
	public boolean registerNewUser(User user) {
		return auth.registerUser(user);
	}
	
	public boolean addPhoneToUser(User user) {
		return auth.addPhoneToUser(user);
	}


	public boolean addPhotoToUser(User u) {
		return auth.addPhotoToUser(u);
	}


	public boolean update(String user) throws JSONException {
		JSONObject object = new JSONObject(user);
		return auth.updateUserInfo(object);
	}


}
