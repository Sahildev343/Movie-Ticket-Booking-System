package com.itvedant.movies.dao;

import java.util.List;

public class AddSeatsDAO {
	
	
private Integer id;
	
	 private boolean isBooked;
	    private String label;
	 private Integer theaterId;
	 private Integer bookingId;
	 
	 public AddSeatsDAO(Integer id, String label, boolean isBooked) {
	        this.id = id;
	        this.label = label;
	        this.isBooked = isBooked;
	    }
	 
	 
	 
	 
	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
//	public Integer getBookingId() {
//		return bookingId;
//	}
//	public void setBookingId(Integer bookingId) {
//		this.bookingId = bookingId;
//	}
//	public String getSeatNumber() {
//		return seatNumber;
//	}
//	public void setSeatNumber(String seatNumber) {
//		this.seatNumber = seatNumber;
//	}
	public boolean isBooked() {
		return isBooked;
	}
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	public Integer getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(Integer theaterId) {
		this.theaterId = theaterId;
	}




	public Integer getBookingId() {
		// TODO Auto-generated method stub
		return null;
	}
	 
	 
}
