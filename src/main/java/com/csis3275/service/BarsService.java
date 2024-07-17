package com.csis3275.service;

import com.csis3275.model.Bar;
import com.csis3275.model.FBUserData;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BarsService {

    FirebaseApp app;

    public BarsService() throws IOException {

        this.app = FirebaseConfig.firebaseApp();
    }

    public List<Bar> getAllBars() throws ExecutionException, InterruptedException {


        Firestore dbFireStore = FirestoreClient.getFirestore(app);

        ApiFuture<QuerySnapshot> future = dbFireStore.collection("bars").get();
        List<Bar> barsList = new ArrayList<>();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {

            Bar nextBar = document.toObject(Bar.class);

            barsList.add(nextBar);
        }



        return barsList;
    }

}
