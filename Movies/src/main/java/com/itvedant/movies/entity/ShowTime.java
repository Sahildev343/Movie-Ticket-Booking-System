package com.itvedant.movies.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ShowTime {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String time;
	
	  private String day;

	    private LocalDate date; // ✅ Add this field for exact date
	
	
	  
	 
	


	public String getDay() {
			return day;
		}


		public void setDay(String day) {
			this.day = day;
		}


		public LocalDate getDate() {
			return date;
		}


		public void setDate(LocalDate date) {
			this.date = date;
		}


	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "theater_id")
	private Theater theater;
	
	
 
	

	
	

	

	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
	}





	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public Theater getTheater() {
		return theater;
	}


	public void setTheater(Theater theater) {
		this.theater = theater;
	}


	
	
	
	
	
}
