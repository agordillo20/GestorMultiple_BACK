package com.agordillo.GestorMultiple.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.agordillo.GestorMultiple.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;

@Service
public class AuthUsersService {
	
	
	private FirebaseAuth auth = FirebaseAuth.getInstance();
	
	public boolean registerUser(User user) {
		try {
			CreateRequest request = new CreateRequest();
			request.setEmail(user.getEmail()).setPassword(user.getPassword()).setDisabled(false).setPhotoUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/User_icon-cp.svg/1200px-User_icon-cp.svg.png");;
			if(user.getPhone()!=null) {
				request.setPhoneNumber(user.getPhone());
			}
			if(user.getUsername()!=null) {
				request.setDisplayName(user.getUsername());
			}
			auth.createUser(request);
			return true;
		} catch (FirebaseAuthException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public boolean addPhoneToUser(User user) {
		try {
			UserRecord u = auth.getUserByEmail(user.getEmail());
			UpdateRequest request = new UpdateRequest(u.getUid()).setPhoneNumber(user.getPhone());
			auth.updateUser(request);
			return true;
		} catch (FirebaseAuthException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean addPhotoToUser(User u) {
		try {
			UserRecord user = auth.getUserByEmail(u.getEmail());
			UpdateRequest request = new UpdateRequest(user.getUid()).setPhotoUrl(u.getPhoto());
			auth.updateUser(request);
			return true;
		} catch (FirebaseAuthException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean updateUserInfo(JSONObject object) throws JSONException {
		String uid = object.getString("uid");
		String email = object.getString("email");
		String displayName = object.getString("displayName");
		String phone = object.getString("phone");
		String foto  = object.getString("url");
		try {
			UserRecord user = auth.getUser(uid);
			UpdateRequest request = new UpdateRequest(uid);
			if(!user.getDisplayName().equals(displayName)) {
				request.setDisplayName(displayName);
			}
			if(!user.getEmail().equals(email)) {
				request.setEmail(email);
			}
			if(!user.getPhoneNumber().equals(phone)) {
				request.setPhoneNumber(phone);
			}
			if(!user.getPhotoUrl().equals(foto)) {
				System.out.println(foto);
				if(foto.startsWith("gs")) {
					foto.replace("gs://","https://storage.googleapis.com/");
				}
				request.setPhotoUrl(foto);
			}else {
				System.out.println("entra en el else");
			}
			
			auth.updateUser(request);
		} catch (FirebaseAuthException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
