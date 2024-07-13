package com.csis3275.service;

import com.csis3275.Csis3275Group2024Application;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import com.csis3275.model.userData;

import javax.swing.text.Document;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
                    .setDatabaseUrl("https://xxxxx.firebaseio.com")
                    .build();

            FirebaseApp app;
            if (FirebaseApp.getApps().isEmpty()) {
                app = FirebaseApp.initializeApp(options, "barcrawl-csis3275");
                System.out.println("initialized Firebase App");
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

    public List<String> getUserNames() throws ExecutionException, InterruptedException {

        FirebaseApp app = getApp();
        Firestore dbFireStore = FirestoreClient.getFirestore(app);
        //DocumentReference documentReference = dbFireStore.collection("user_data").document();
        //ApiFuture<DocumentSnapshot> future = documentReference.get();
        //DocumentSnapshot document = future.get();

        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFireStore.collection("user_data").get();
        // future.get() blocks on response
        List<String> userList = new ArrayList<>();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(userData.class));
            String username = document.getId();
            userList.add(username);
        }





        return userList;
    }


}
