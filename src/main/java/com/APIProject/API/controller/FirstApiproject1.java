package com.APIProject.API.controller;

import com.APIProject.API.dto.CountriesDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/country")
public class FirstApiproject1 {

    private List<CountriesDto> countries = new ArrayList<>();

    //CREATE - POST
    @PostMapping
    public ResponseEntity<CountriesDto> save(@RequestBody final CountriesDto countriesDto){
        countries.add(countriesDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(countriesDto);
    }

    //READ - GET
    // read all

    @GetMapping
    public ResponseEntity<List<CountriesDto>> getAll(){
        var country = new CountriesDto(1,"Brazil",100_000_000L);
        countries.add(country);

        return ResponseEntity.ok(countries);
    }

    //read specific
    @GetMapping("/{id}")
    public ResponseEntity<CountriesDto> findById(@PathVariable("id") final long id){
        for(var country: countries){
            if (country.getId() == id){
                return ResponseEntity.ok(country);
            }
        }
        return ResponseEntity.notFound().build();
    }
    //filterin
    //UPDATE -PUT/PATCH
    //DELETE-DELETE

    @GetMapping("/FirstAPI1")
    public String firstAPI(){
        return "FirstAPI";
    }

}
