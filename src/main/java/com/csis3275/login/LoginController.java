package com.csis3275.login;

import com.csis3275.service.FirebaseConfig;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    FirebaseApp app;

    public LoginController() throws IOException {

        this.app = FirebaseConfig.firebaseApp();
    }

    @RequestMapping(value = "/yourURL", method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public List<String> reqControl(@RequestBody Map<String,String> myMap) throws FirebaseAuthException {
        List<String> list = new ArrayList<String>();

        for (Map.Entry<String,String> entry : myMap.entrySet()) {
            list.add(entry.getValue());
        }

        //System.out.println(list.get(0));
        String idToken = list.get(0);
        FirebaseToken decodedToken = FirebaseAuth.getInstance(app).verifyIdToken(idToken);
        String uid = decodedToken.getUid();
        System.out.println(uid);

        return list;
    }
}
