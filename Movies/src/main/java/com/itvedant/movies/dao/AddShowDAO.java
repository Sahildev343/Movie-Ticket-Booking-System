package com.itvedant.movies.dao;

import com.itvedant.movies.entity.Movie;


public class AddShowDAO {

	private String showTime;
	private Integer availableSeats;
	private Integer totalSeats;
	private Integer movieId;  
	
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public Integer getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Integer getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}
	public Integer getMovie() {
		return movieId;
	}
	public void setMovie(Integer movie) {
		this.movieId = movie;
	}

	
	
}
