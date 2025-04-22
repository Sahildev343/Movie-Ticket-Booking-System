package com.itvedant.movies.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BookingResponse {
    private Integer id;
//    private String userEmail;
    private Integer userId;
    private MovieDTO movie;
    private TheaterDTO theater;
    private List<String> bookedSeats;
    private LocalDate bookingTime;
    private String bookingStatus;
    private String message;
    private LocalDate showDate;
    private String showTime;

  

    // ✅ Constructor
 // Updated Constructor
    public BookingResponse(Integer id, Integer userId, MovieDTO movie, TheaterDTO theater, List<String> bookedSeats, LocalDate bookingTime, String bookingStatus, String message, LocalDate showDate, String showTime) {
        this.id = id;
        this.userId = userId;
        this.movie = movie;
        this.theater = theater;
        this.bookedSeats = bookedSeats;
        this.bookingTime = bookingTime;
        this.bookingStatus = bookingStatus;
        this.message = message;
        this.showDate = showDate;
        this.showTime = showTime;
    }

  
  


    // ✅ Getters & Setters
    public Integer getId() { return id; }
    public Integer getUserId() { return userId; }
    public MovieDTO getMovie() { return movie; }
    public TheaterDTO getTheater() { return theater; }
    public List<String> getBookedSeats() { return bookedSeats; }
    public LocalDate getBookingTime() { return bookingTime; }
    public String getMessage() { return message; }
    public String getBookingStatus() { return bookingStatus;}
    public LocalDate getShowDate() { return showDate; }
    public String getShowTime() { return showTime; }
}
