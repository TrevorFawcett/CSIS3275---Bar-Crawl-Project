package com.csis3275;

import com.csis3275.service.FirebaseConfig;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

@SpringBootApplication
public class Csis3275Group2024Application {

    public static FirebaseApp mainApp;

    public static void main(String[] args) throws IOException {

        /*try {
            ClassLoader classLoader = Csis3275Group2024Application.class.getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("ServiceAccountKey.json")).getFile());
            InputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
            //System.out.println(file.toString());

            //String credentials = String.valueOf(GoogleCredentials.fromStream(serviceAccount));
            //System.out.println(credentials);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp app;
            if (FirebaseApp.getApps().isEmpty()) {
                app = FirebaseApp.initializeApp(options, "barcrawl-csis3275");
                System.out.println("initialized Firebase App");
            } else {
                app = FirebaseApp.getApps().get(0);
            }

            String projectId = app.getOptions().getProjectId();

            FirestoreOptions firestoreOptions =
                    FirestoreOptions.getDefaultInstance().toBuilder()
                            .setProjectId(projectId)
                            .setCredentials(GoogleCredentials.getApplicationDefault())
                            .build();

            Firestore db = firestoreOptions.getService();

        } catch (IOException e) {
            System.out.println("file not found");
        }
        */

        /*
        try {
            ClassLoader classLoader = Csis3275Group2024Application.class.getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("ServiceAccountKey.json")).getFile());
            InputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

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
            }


        }
        catch (IOException e) {
            System.out.println("ERROR: invalid service account credentials. See README.");
            System.out.println(e.getMessage());

        } */

        mainApp = FirebaseConfig.firebaseApp();

        SpringApplication.run(Csis3275Group2024Application.class, args);
    }



}
