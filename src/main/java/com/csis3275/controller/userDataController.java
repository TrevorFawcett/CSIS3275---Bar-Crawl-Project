package com.csis3275.controller;

import com.csis3275.model.userData;
import com.csis3275.service.userDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.net.MediaType;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.*
//import org.springframework.security.crypto.bcrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class userDataController {

    public userDataService userService;

    //private String hashPassword(String string){
        //BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        //String p1 = bc.encode(string);
        //return p1;
    //}

    private boolean checkList(String username) throws ExecutionException, InterruptedException {
        List<String> userList = userService.getUserNames();

        if (userList.contains(username)) {
            return true;
        }
        else{
            return false;
        }

    }

    public userDataController(userDataService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("user") userData user) throws ExecutionException, InterruptedException, JsonProcessingException {
        //System.out.println(user);
        String username = user.getUsername();
        user.setDocument_id(username);

        //String hashed = hashPassword(user.getPassword());
        //user.setPassword(hashed);

        if(checkList(user.getDocument_id())){
            System.out.println("username already exists");
            return "signup";
        }
        else{
            userService.createUserData(user);
            return "homepage";
        }



    }

    @PostMapping("/create")
    public String createData(@RequestBody userData user) throws InterruptedException, ExecutionException
    {
        return userService.createUserData(user);
    }

    @GetMapping("/get")
    public userData getUser(@RequestParam String document_id) throws InterruptedException, ExecutionException
    {
        return userService.getUserData(document_id);
    }

    //@PutMapping("/update)
    //@PutMapping("/delete)

}
