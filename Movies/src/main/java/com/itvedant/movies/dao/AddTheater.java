package com.itvedant.movies.dao;

public class AddTheater {

	 private String name;
	 private String location;
	 
	 private Integer totalSeats;
	    private Integer availableSeats;
	    
	    
	public Integer getTotalSeats() {
			return totalSeats;
		}
		public void setTotalSeats(Integer totalSeats) {
			this.totalSeats = totalSeats;
		}
		public Integer getAvailableSeats() {
			return availableSeats;
		}
		public void setAvailableSeats(Integer availableSeats) {
			this.availableSeats = availableSeats;
		}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	 
	 
}
