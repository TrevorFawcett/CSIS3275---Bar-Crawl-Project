package com.csis3275.controller;

import com.csis3275.model.userData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class Main_Controller {

//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("greeting", new GreetingPage());
//
//        return "greeting";
//    }



    @GetMapping("/")
    public String runHome(Model model){
        return "homepage";
    }

    @GetMapping("/signup-page")
    public String signUpPage(Model model){
        userData userNew = new userData();
        model.addAttribute("userNew",userNew);


        return "signup";
    }

    @GetMapping("/login-page")
    public String loginPage(Model model){
        return "loginpage";
    }

//    @PostMapping("/register")
//    public String submitForm(@ModelAttribute("user") userData user) {
//        System.out.println(user);
//        String username = user.getUsername();
//        user.setDocument_id(username);
//        System.out.println(user.getDocument_id());
//        System.out.println(user.getDob());
//        return "homepage";
//    }
}



