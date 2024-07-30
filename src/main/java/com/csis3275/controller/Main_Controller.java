package com.csis3275.controller;

import com.csis3275.model.Bar;
import com.csis3275.model.BarList;
import com.csis3275.model.searchQuery;
import com.csis3275.model.userFormData;
import com.csis3275.service.BarsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Controller
public class Main_Controller {

//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("greeting", new GreetingPage());
//
//        return "greeting";
//    }

    public BarsService barService;
    public BarList listofbars;

    public Main_Controller(BarsService barService) {
        this.barService = barService;
        this.listofbars = new BarList();
    }

    @GetMapping("/")
    public String runHome(Model model) {
        searchQuery q = new searchQuery();
        model.addAttribute("searchQuery", q);

        return "homepage";
    }

    @GetMapping("/signup-page")
    public String signUpPage(Model model) {
        userFormData userNew = new userFormData();
        model.addAttribute("userNew", userNew);


        return "signup";
    }

    @GetMapping("/login-page")
    public String loginPage(Model model) {
        return "login2";
    }

    @PostMapping("/search")
    public String searchResults(@ModelAttribute("searchQuery") searchQuery input, Model model) throws ExecutionException, InterruptedException {
        String result = input.getUserInput();
        System.out.println(result);

        List<Bar> barsList = barService.getAllBars();
        List<Bar> searchResults = new ArrayList<>();

        for(Bar bar: barsList){

            if(bar.getCity().equals(result))
            {
                searchResults.add(bar);
            }
        }
        System.out.println(searchResults);



        listofbars.setBars(searchResults);


        return "redirect:/results";
    }

    @GetMapping("/results")
    public String showResults(Model model) {
        List<Bar> displayBars = listofbars.getBars();


        System.out.println(displayBars);
        model.addAttribute("displayBars", displayBars);

        return "resultspage";
    }


    @GetMapping("/AboutUs")
    public String AboutUs(Model model) {


        return "AboutUs";
    }
    @GetMapping("/Franchise")
    public String Franchise(Model model) {


        return "Franchise";
    }
    @GetMapping("/ContactUs")
    public String ContactUs(Model model) {


        return "ContactUs";
    }








}



