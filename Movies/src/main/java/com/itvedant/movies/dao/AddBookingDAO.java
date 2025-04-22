package com.itvedant.movies.dao;

import java.time.LocalDate;
import java.util.List;

public class AddBookingDAO {
	
	  private Integer movieId;
	  private Integer theaterId;
	  private List<String> selectedSeats;
	  private Integer showTimeId;
	  private Integer userId;
	  private LocalDate selectedDate;
	  private Integer ticketPrice;
	  
	    
	    
	    
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

	public Integer getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(Integer ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public LocalDate getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(LocalDate selectedDate) {
		this.selectedDate = selectedDate;
	}
	public List<String> getSelectedSeats() {
		return selectedSeats;
	}
	public void setSelectedSeats(List<String> selectedSeats) {
		this.selectedSeats = selectedSeats;
	}
	public List<String> getBookedSeats() {
		return selectedSeats;
	}
	public void setBookedSeats(List<String> bookedSeats) {
		this.selectedSeats = bookedSeats;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userEmail) {
		this.userId = userEmail;
	}
	public Integer getShowTimeId() {
		return showTimeId;
	}
	public void setShowTimeId(Integer showTimeId) {
		this.showTimeId = showTimeId;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public Integer getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(Integer theaterId) {
		this.theaterId = theaterId;
	}

	  
	  
	
	
	
	
	
	
}
