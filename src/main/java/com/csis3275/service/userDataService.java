package com.csis3275.service;

import com.csis3275.model.FBUserData;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.csis3275.model.userFormData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.csis3275.Csis3275Group2024Application.userStore;

@Service
public class userDataService {

    FirebaseApp app;


    public userDataService() throws IOException {
        this.app = FirebaseConfig.firebaseApp();
    }

    public String createUserData(FBUserData userdata) throws InterruptedException, ExecutionException
    {

        Firestore dbFireStore = FirestoreClient.getFirestore(app);

        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection("user_data").document(userdata.getDocument_id()).set(userdata);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }


    public void getUserData(String document_id) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore(app);
        FBUserData user;

        ApiFuture<QuerySnapshot> future = dbFireStore.collection("user_data").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.getId().equals(document_id)) {
                String documentId = document.getData().get("document_id").toString();
                String username = document.getData().get("username").toString();
                String email = document.getData().get("email").toString();
                String dob = document.getData().get("dob").toString();
                //String firstName = document.getData().get("first_name").toString();
                String firstName;
                String lastName;
                if(document.getData().get("first_name")== null){
                    firstName = "";
                }
                else{
                    firstName = document.getData().get("first_name").toString();
                }

                if(document.getData().get("last_name")== null){
                    lastName = "";
                }
                else{
                    lastName = document.getData().get("last_name").toString();
                }


                user = new FBUserData(documentId, username, email, dob, firstName, lastName);
                userStore.addActiveUser(user);

                System.out.println(userStore.getActiveUsers());

            }
        }




    }

    public List<String> getUserNames() throws ExecutionException, InterruptedException {


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
            System.out.println(document.getId() + " => " + document.toObject(FBUserData.class));
            FBUserData nextUser = document.toObject(FBUserData.class);
            String username = nextUser.getUsername();
            System.out.println(username);
            userList.add(username);
        }





        return userList;
    }

    public ActiveUserStore getUserStore() {
        return userStore;
    }
}
