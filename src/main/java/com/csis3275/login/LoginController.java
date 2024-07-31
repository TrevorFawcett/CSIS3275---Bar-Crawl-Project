package com.csis3275.login;

import com.csis3275.model.FBUserData;
import com.csis3275.service.ActiveUserStore;
import com.csis3275.service.FirebaseConfig;
import com.csis3275.service.userDataService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.csis3275.Csis3275Group2024Application.userService;
import static com.csis3275.Csis3275Group2024Application.userStore;

@Controller
public class LoginController {

    FirebaseApp app;


    public LoginController() throws IOException {

        this.app = FirebaseConfig.firebaseApp();
    }

    @RequestMapping(value = "/yourURL", method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public String reqControl(@RequestBody Map<String,String> myMap) throws FirebaseAuthException, ExecutionException, InterruptedException {
        List<String> list = new ArrayList<String>();

        for (Map.Entry<String,String> entry : myMap.entrySet()) {
            list.add(entry.getValue());
        }

        //System.out.println(list.get(0));
        String idToken = list.get(0);
        FirebaseToken decodedToken = FirebaseAuth.getInstance(app).verifyIdToken(idToken);
        String uid = decodedToken.getUid();
        System.out.println(uid);

        userService.getUserData(uid);




       return "/login";
    }

    @GetMapping("/login")
    public String userLoggedIn(Model model) throws InterruptedException {

        Thread.sleep(5000);
        System.out.println(userStore.getActiveUsers());
        List<FBUserData> list = userStore.getActiveUsers();
        FBUserData newUser = list.get(0);
        model.addAttribute("user", newUser);

        return "userLogin";
    }
}
