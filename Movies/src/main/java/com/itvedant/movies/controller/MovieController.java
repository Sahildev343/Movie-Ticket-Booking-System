package com.itvedant.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itvedant.movies.dao.AddMovie;
import com.itvedant.movies.entity.Movie;
import com.itvedant.movies.service.MovieService;


@Controller
@RequestMapping("movies")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend requests
public class MovieController {

	
	@Autowired
	private MovieService movieService;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody AddMovie addMovie) {
		
		return ResponseEntity.ok(this.movieService.create(addMovie));
	}
	
	
	@GetMapping
	public ResponseEntity<?> read() {
		
		return ResponseEntity.ok(this.movieService.read());
	}
	
	 @PutMapping("/{id}")
	    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody AddMovie updatedMovie) {
	        return ResponseEntity.ok(this.movieService.update(id, updatedMovie));
	    }

	   
	 @DeleteMapping("/{id}")
	 public ResponseEntity<?> delete(@PathVariable Integer id) {
	        return ResponseEntity.ok(this.movieService.delete(id));
	 }
	 
	 @PutMapping("/assign-theater-to-all/{theaterId}")
	 public ResponseEntity<String> assignTheaterToAll(@PathVariable Integer theaterId) {
	     String result = movieService.assignTheaterToAllMovies(theaterId);
	     return ResponseEntity.ok(result);
	 }
	 
	 @GetMapping("/by-theater/{theaterId}")
	    public ResponseEntity<List<Movie>> getMoviesByTheater(@PathVariable Integer theaterId) {
	        List<Movie> movies = movieService.getMoviesByTheaterId(theaterId);
	        return ResponseEntity.ok(movies);
	    }

}
