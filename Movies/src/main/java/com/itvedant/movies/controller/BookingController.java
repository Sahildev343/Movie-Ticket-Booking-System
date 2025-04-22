 package com.itvedant.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itvedant.movies.dao.AddBookingDAO;
import com.itvedant.movies.dao.BookingResponse;
import com.itvedant.movies.entity.Booking;
import com.itvedant.movies.service.BookingService;


import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> bookTicket(@RequestBody AddBookingDAO bookingRequest) {
        BookingResponse booking = bookingService.bookTicket(
                bookingRequest.getShowTimeId(),
                bookingRequest.getUserId(),
                bookingRequest.getSelectedSeats(),
                bookingRequest.getMovieId()
               
                
        );
        return ResponseEntity.status(201).body(booking);
    }
    
//    @GetMapping("/booked-seats")
//    public ResponseEntity<List<String>> getBookedSeats(@RequestParam Integer showTimeId) {
//        List<String> bookedSeats = bookingService.getBookedSeatsByShowTime(showTimeId);
//        return ResponseEntity.ok(bookedSeats);
//    }
    
    @GetMapping("/booked-seats/{showTimeId}")
    public ResponseEntity<List<String>> getBookedSeats(@PathVariable Integer showTimeId) {
        List<String> bookedSeats = bookingService.getBookedSeatsByShowTimes(showTimeId);
        return ResponseEntity.ok(bookedSeats);
    }
    
   
    
    // ✅ Get Booking Details By Id
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Integer bookingId) {
        Booking booking = this.bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }
    
    
    
    @GetMapping("/mybookings")
    public ResponseEntity<List<BookingResponse>> getMyBookings(@RequestParam Integer userId) {
        List<BookingResponse> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }
    
    
}
 






























//package com.itvedant.movies.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.itvedant.movies.dao.AddBookingDAO;
//import com.itvedant.movies.entity.Booking;
//import com.itvedant.movies.service.BookingService;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/bookings")
//public class BookingController {
//
//    @Autowired
//    private BookingService bookingService;
//
//    //Book a Ticket
//    @PostMapping
//    public ResponseEntity<Booking> bookTicket(@RequestBody AddBookingDAO bookingRequest) {
//        Booking booking = bookingService.bookTicket(
//                bookingRequest.getShowTimeId(),
//                bookingRequest.getUserEmail(),
//                bookingRequest.getSelectedSeats()
//        );
//        return ResponseEntity.ok(booking);
//    }
//}
