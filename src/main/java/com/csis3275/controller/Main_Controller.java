package com.csis3275.controller;

import com.csis3275.model.GreetingPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class Main_Controller {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("greeting", new GreetingPage());

        return "greeting";
    }

}



