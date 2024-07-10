package com.csis3275.service;

import com.csis3275.Csis3275Group2024Application;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import com.csis3275.model.userData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class userDataService {

    public FirebaseApp getApp(){
        try {
            ClassLoader classLoader = Csis3275Group2024Application.class.getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("ServiceAccountKey.json")).getFile());
            InputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
            //System.out.println(file.toString());



            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp app;
            if (FirebaseApp.getApps().isEmpty()) {

            } else {
                app = FirebaseApp.getApps().get(0);
                System.out.println(app.toString());

                return app;
            }

        } catch (IOException e) {
            System.out.println("file not found");
        }


        return null;
    }

    public String createUserData(userData userdata) throws InterruptedException, ExecutionException
    {
        FirebaseApp app = getApp();
        Firestore dbFireStore = FirestoreClient.getFirestore(app);
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection("user_data").document(userdata.getUsername()).set(userdata);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public userData getUserData(String document_id) {

        return null;
    }


}
