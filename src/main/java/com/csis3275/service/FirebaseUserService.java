package com.csis3275.service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FirebaseUserService {

    FirebaseApp app;

    public FirebaseUserService() throws IOException {

        this.app = FirebaseConfig.firebaseApp();
    }

    public UserRecord createUser(String email, String password) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(email)
                .setPassword(password);


        return FirebaseAuth.getInstance(app).createUser(request);
    }

    // Add more methods as needed for user management
}