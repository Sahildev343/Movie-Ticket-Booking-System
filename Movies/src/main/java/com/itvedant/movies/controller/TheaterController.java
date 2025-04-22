package com.itvedant.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.movies.dao.AddTheater;
import com.itvedant.movies.entity.Theater;
import com.itvedant.movies.service.TheaterService;

@RestController
@RequestMapping("/theaters")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;
    
    
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AddTheater addTheater) {
    	
    	return ResponseEntity.ok(this.theaterService.create(addTheater));
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Integer id) {
        Theater theater = theaterService.getTheaterById(id);
        return ResponseEntity.ok(theater);
    }


    @GetMapping("/movie/{movieId}")
    public List<Theater> getTheatersByMovie(@PathVariable Integer movieId) {
        return this.theaterService.getTheatersByMovie(movieId); 
    }

}

