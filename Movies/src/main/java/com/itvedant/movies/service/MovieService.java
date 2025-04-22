package com.itvedant.movies.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itvedant.movies.Role;
import com.itvedant.movies.dao.AddMovie;
import com.itvedant.movies.entity.Movie;
import com.itvedant.movies.entity.Theater;
import com.itvedant.movies.entity.User;
import com.itvedant.movies.repository.MoviesRepository;
import com.itvedant.movies.repository.TheaterRepository;
import com.itvedant.movies.repository.UserRepository;



@Service
public class MovieService {

	
	@Autowired
	private MoviesRepository moviesRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private TheaterRepository theaterRepository;
	
	
	public Movie create(AddMovie addMovie) {
		
		  List<Theater> theater = this.theaterRepository.findAllById(List.of(addMovie.getTheaterId()));
		
		
		Movie movie  = new Movie();
		movie.setTitle(addMovie.getTitle());
		movie.setGenre(addMovie.getGenre());
		movie.setDirector(addMovie.getDirector());
		movie.setDuration(addMovie.getDuration());
		movie.setMoviePoster(addMovie.getMoviePoster());
		movie.setTheaters(theater);
		
		this.moviesRepository.save(movie);
		
		return movie;
	}
	
	public List<Movie> read() {
		
		List<Movie> li = new ArrayList<Movie>();
		
		
		li =  this.moviesRepository.findAll();
		
		return li;
	}
	
    public Movie update(Integer id, AddMovie updatedMovie) {
        Optional<Movie> existingMovie = this.moviesRepository.findById(id);
        if (existingMovie.isPresent()) {
            Movie movie = existingMovie.get();
            movie.setTitle(updatedMovie.getTitle());
            movie.setGenre(updatedMovie.getGenre());
            movie.setDirector(updatedMovie.getDirector());
            movie.setDuration(updatedMovie.getDuration());
            return this.moviesRepository.save(movie);
        }
        return null; // Handle error properly in a real application
    }

    // ✅ DELETE MOVIE
    public String delete(Integer id) {
       this.moviesRepository.deleteById(id);
        return "Movie Deleted Successfully!";
    }
    
    public List<Movie> getMoviesByTheaterId(Integer theaterId) {
        Theater theater = this.theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found with ID: " + theaterId));

        return moviesRepository.findByTheater(theater);
    }
    
    
    @Transactional
    public String assignTheaterToAllMovies(Integer theaterId) {
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found with ID: " + theaterId));

        List<Movie> movies = moviesRepository.findAll();

        for (Movie movie : movies) {
            if (!movie.getTheaters().contains(theater)) {
                movie.getTheaters().add(theater); // This is key
                System.out.println("Assigning theater to movie: " + movie.getTitle());
            }
        }

        moviesRepository.saveAll(movies); // This persists the join table entries

        return "Theater assigned to all movies successfully.";
    }


}
