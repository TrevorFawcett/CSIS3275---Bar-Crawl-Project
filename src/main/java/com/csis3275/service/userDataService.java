package com.csis3275.service;

import com.csis3275.model.FBUserData;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import com.csis3275.model.userFormData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public userFormData getUserData(String document_id) {


        return null;
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


}
