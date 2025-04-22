package com.itvedant.movies.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itvedant.movies.StringListConverter;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnoreProperties("bookings") 
    private Movie movie;

    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    @JsonIgnoreProperties({"movies", "showTimes"})  
    private Theater theater;

    @Column(columnDefinition = "TEXT")  // ✅ Store as comma-separated values
    @Convert(converter = StringListConverter.class) 
    private List<String> bookedSeats;

    private LocalDate bookingDate;
    
    
    
    private String bookingStatus;

    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    
    
    
    
    
    

   
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	

	@ManyToOne
    @JsonBackReference
    @JoinColumn(name = "showtime_id")
    private ShowTime showtime; 
    
    
   

    // ✅ Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public List<String> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<String> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public LocalDate getBookingTime() {
        return bookingDate;
    }

    public void setBookingTime(LocalDate bookingTime) {
        this.bookingDate = bookingTime;
    }

    public ShowTime getShowtime() {
        return showtime;
    }

    public void setShowtime(ShowTime showtime) {
        this.showtime = showtime;
    }
}
