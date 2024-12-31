package com.APIProject.API.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/country")
public class FirstApiproject1 {

    private List<String> countries = new ArrayList<>();

    //CREATE - POST
    //READ - GET
    // read all

    @GetMapping
    public List<String> getall(){
        return countries;
    }

    //read specific
    //filterin
    //UPDATE -PUT/PATCH
    //DELETE-DELETE

    @GetMapping("/FirstAPI1")
    public String firstAPI(){
        return "FirstAPI";
    }

}
