package com.itvedant.movies.dao;

import java.time.LocalDate;

public class AddShowTimesDAO {

	
	private String time;
	private Integer theaterId;
	private Integer movieId;
	private LocalDate date;
	
	
	
	
	
	
	
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(Integer theaterId) {
		this.theaterId = theaterId;
	}
	
	
}
