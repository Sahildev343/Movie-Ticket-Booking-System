package com.itvedant.movies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itvedant.movies.Role;
import com.itvedant.movies.dao.AddTheater;
import com.itvedant.movies.entity.Movie;
import com.itvedant.movies.entity.Theater;
import com.itvedant.movies.entity.User;
import com.itvedant.movies.repository.MoviesRepository;
import com.itvedant.movies.repository.TheaterRepository;
import com.itvedant.movies.repository.UserRepository;

@Service
public class TheaterService {

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private TheaterRepository theaterRepository;
    
    
    @Autowired
    private UserRepository userRepository;
    
    public Theater ById(Integer theaterId) {
    	Theater theater = new Theater();
    	theater = this.theaterRepository.findById(theaterId).orElse(null);
    	
    	return theater;
    }

    public Theater create(AddTheater addTheater) {
       

        Theater theater = new Theater();
        theater.setName(addTheater.getName());
        theater.setLocation(addTheater.getLocation());
        theater.setTotalSeats(addTheater.getTotalSeats());
        theater.setAvailableSeats(addTheater.getAvailableSeats());
        
    

        return theaterRepository.save(theater);
    }
    
//    public void updateTheater(Integer adminId, Theater updatedTheater) {
//        User admin = this.userRepository.findById(adminId)
//                .orElseThrow(() -> new RuntimeException("Admin Not Found!"));
//
//        if (admin.getRole() != Role.THEATER_ADMIN) {
//            throw new RuntimeException("Access Denied! Only Theater Admins can update theaters.");
//        }
//
//        Theater theater = admin.getTheater();
//        theater.setName(updatedTheater.getName());
//        theater.setLocation(updatedTheater.getLocation());
//
//        theaterRepository.save(theater);
//    }

    public List<Theater> getTheatersByMovie(Integer movieId) {
        return theaterRepository.findByMovieId(movieId);  
    }
    
    public Theater getTheaterById(Integer theaterId) {
        return theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found with ID: " + theaterId));
    }
    
    public void approveTheater(Integer theaterId) {
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater Not Found!"));

        theater.setApproved(true); 
        theaterRepository.save(theater);
    }
    
//    public List<Theater> getPendingTheaters() {
//        return theaterRepository.findByApprovedFalse();
//    }
//    
//    public List<Theater> getApprovedTheaters() {
//        return theaterRepository.findByApprovedTrue();
//    }


    
}
