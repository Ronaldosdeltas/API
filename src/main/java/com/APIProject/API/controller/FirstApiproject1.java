package com.APIProject.API.controller;

import com.APIProject.API.dto.CountriesDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/country")
public class FirstApiproject1 {

    private List<CountriesDto> countries = insertCountries();

    private List<CountriesDto>  insertCountries(){
        var listofCountries = new ArrayList<CountriesDto>();
        listofCountries.add(new CountriesDto(1,"Brazil",100_000_000l));
        listofCountries.add(new CountriesDto(7,"Canada",100_000_000l));
        listofCountries.add(new CountriesDto(4,"Russia",100_000_000l));
        listofCountries.add(new CountriesDto(5,"Japan",100_000_000l));
        listofCountries.add(new CountriesDto(2,"Usa",100_000_000l));
        return listofCountries;

    }


    //CREATE - POST
    @PostMapping
    public ResponseEntity<CountriesDto> save(@RequestBody final CountriesDto countriesDto){
        countries.add(countriesDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(countriesDto);
    }

    //READ - GET
    // read all

    //@GetMapping
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
    @GetMapping
public ResponseEntity<List<CountriesDto>> getAll(
        @RequestParam(name = "prefix",required = false) final String prefix){
        if(Objects.isNull(prefix)){
            return ResponseEntity.ok(countries);
        }
        else{
            var listofCountries =
                    countries.stream()
                            .filter(countriesDto -> countriesDto.getName().startsWith(prefix))
                            .collect(Collectors.toList());
            return ResponseEntity.ok(listofCountries);

        }


}
    //UPDATE -PUT/PATCH
    //DELETE-DELETE

    @GetMapping("/FirstAPI1")
    public String firstAPI(){
        return "FirstAPI";
    }

}
