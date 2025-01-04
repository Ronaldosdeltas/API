package com.APIProject.API.controller;

import com.APIProject.API.dto.CountriesRequest;
import com.APIProject.API.dto.CountriesResponse;
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

    private List<CountriesResponse> countries = insertCountries();

    private List<CountriesResponse>  insertCountries(){
        var listofCountries = new ArrayList<CountriesResponse>();
        listofCountries.add(new CountriesResponse(1,"Brazil",100_000_000l));
        listofCountries.add(new CountriesResponse(7,"Canada",100_000_000l));
        listofCountries.add(new CountriesResponse(4,"Russia",100_000_000l));
        listofCountries.add(new CountriesResponse(5,"Japan",100_000_000l));
        listofCountries.add(new CountriesResponse(2,"Usa",100_000_000l));
        return listofCountries;

    }


    //CREATE - POST
    @PostMapping
    public ResponseEntity<CountriesResponse> save(@RequestBody final CountriesRequest request){
        Integer id = countries.size();
        var response = new CountriesResponse(id, request.getName(), request.getPopulation());
        countries.add(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //READ - GET
    // read all

    //@GetMapping
    public ResponseEntity<List<CountriesResponse>> getAll(){
        var country = new CountriesResponse(1,"Brazil",100_000_000L);
        countries.add(country);

        return ResponseEntity.ok(countries);
    }

    //read specific
    @GetMapping("/{id}")
    public ResponseEntity<CountriesResponse> findById(@PathVariable("id") final long id){
        for(var country: countries){
            if (country.getId() == id){
                return ResponseEntity.ok(country);
            }
        }
        return ResponseEntity.notFound().build();
    }
    //filterin
    @GetMapping
public ResponseEntity<List<CountriesResponse>> getAll(
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
    @PutMapping("/{id}")
    public ResponseEntity<CountriesResponse> update(@PathVariable("id") final int id, @RequestBody final CountriesRequest request){

        CountriesResponse countriesDto = null;
        for(var country: countries){
            if (country.getId() == id){
                countriesDto = country;

            }
        }
        if(Objects.nonNull(countriesDto)){
            countriesDto.setName(request.getName());
            countriesDto.setPopulation(request.getPopulation());
            return ResponseEntity.ok(countriesDto);
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }
    //DELETE-DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final int id){

        //Find
        int index = -1;
        for(int i = 0; i<countries.size(); ++i){
            if(countries.get(i).getId() ==id){
                index =i;
                break;
            }
        }
        //remove
        if(index >= 0){
            countries.remove(index);
        }
        return ResponseEntity.noContent().build();

    }
    @GetMapping("/FirstAPI1")
    public String firstAPI(){
        return "FirstAPI";
    }

}
