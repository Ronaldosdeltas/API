package com.APIProject.API.controller;

import com.APIProject.API.dto.CountriesDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/country")
public class FirstApiproject1 {

    private List<CountriesDto> countries = new ArrayList<>();

    //CREATE - POST
    @PostMapping
    public CountriesDto save(@RequestBody final CountriesDto countriesDto){
        countries.add(countriesDto);
        return countriesDto;
    }

    //READ - GET
    // read all

    @GetMapping
    public List<CountriesDto> getall(){
        var country = new CountriesDto(1,"Brazil",100_000_00L);
        countries.clear();
        countries.add(country);

        return countries;
    }

    //read specific
    public CountriesDto findById(@PathVariable("id") final long id){
        for(var country: countries){
            if (country.getId() == id){
                return country;
            }
        }
        return null;
    }
    //filterin
    //UPDATE -PUT/PATCH
    //DELETE-DELETE

    @GetMapping("/FirstAPI1")
    public String firstAPI(){
        return "FirstAPI";
    }

}
