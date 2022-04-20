package com.agordillo.GestorMultiple.config;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.rpc.context.AttributeContext.Resource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.agordillo.GestorMultiple.GestorMultipleApplication;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FirebaseConfig {
	
    @Bean
    public Firestore firestore() throws IOException{
    	
    	
    	
		//var fileKey = new ClassPathResource("keyFirebase.json", GestorMultipleApplication.class.getClassLoader()).getFile();
		var serviceAccount = GestorMultipleApplication.class.getClassLoader().getResourceAsStream("keyFirebase.json");

		FirebaseOptions options = FirebaseOptions.builder()
		.setCredentials(GoogleCredentials.fromStream(serviceAccount))
		.build();
		var fbapp = FirebaseApp.initializeApp(options);
        return FirestoreClient.getFirestore(fbapp);
	}
    
}
