package com.itvedant.movies.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.itvedant.movies.dao.AddSeatsDAO;
import com.itvedant.movies.entity.Seat;
import com.itvedant.movies.entity.ShowTime;
import com.itvedant.movies.entity.Theater;
import com.itvedant.movies.repository.BookingRepository;
import com.itvedant.movies.repository.ShowTimeRespository;
import com.itvedant.movies.service.SeatService;

@RestController
@RequestMapping("/seats")
public class SeatController {
    @Autowired
    private SeatService seatService;
    
    
    @Autowired
    private BookingRepository bookingRepository;
    
    
    @Autowired
    private ShowTimeRespository showTimeRespository;
    
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AddSeatsDAO addSeatsDAO) {
    	
    	return ResponseEntity.ok(this.seatService.create(addSeatsDAO));
    }

  
    
    @GetMapping("/theater/{theaterId}")
    public List<AddSeatsDAO> getSeatsByTheater(@PathVariable Integer theaterId) {
        return seatService.getSeatsByTheater(theaterId);
    }
//    
//    @GetMapping("/{showTimeId}")
//    public List<Map<String, Object>> getSeatsByShowTime(@PathVariable Integer showTimeId) {
//        List<Map<String, Object>> seats = new ArrayList<>();
//
//        // Fetch ShowTime and its associated Theater
//        ShowTime showTime = this.showTimeRespository.findById(showTimeId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ShowTime not found"));
//
//        Theater theater = showTime.getTheater();
//        if (theater == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Theater not found for this ShowTime");
//        }
//
//        int totalSeats = theater.getTotalSeats();
//
//        // Fetch booked seats (returns List<List<String>>)
//        List<List<String>> bookedSeatsList = bookingRepository.findBookedSeatsByShowtime(showTimeId);
//
//        // Flatten the List<List<String>> into List<String>
//        List<String> bookedSeats = bookedSeatsList.stream()
//                .flatMap(List::stream)
//                .collect(Collectors.toList());
//
//        // Generate seat labels dynamically
//        for (int i = 0; i < totalSeats; i++) {
//            Map<String, Object> seat = new HashMap<>();
//            String row = String.valueOf((char) ('A' + (i / 10))); // A, B, C...
//            String seatLabel = row + (i % 10 + 1);
//
//            seat.put("seatNumber", seatLabel);
//            seat.put("booked", bookedSeats.contains(seatLabel));
//
//            seats.add(seat);
//        }
//
//        return seats;
//    }
    
    @GetMapping("/{showTimeId}")
    public List<Map<String, Object>> getSeatsByShowTime(@PathVariable Integer showTimeId) {
        return seatService.getSeatsByShowTime(showTimeId);
    }



    
    @GetMapping("/{theaterId}/{showTimeId}")
    public ResponseEntity<List<String>> getAvailableSeats(@PathVariable Integer theaterId, @PathVariable Integer showTimeId) {
        List<String> seats = seatService.getAvailableSeats(theaterId, showTimeId);
        return ResponseEntity.ok(seats);
    }
    
   
}

