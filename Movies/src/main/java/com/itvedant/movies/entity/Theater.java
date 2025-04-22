package com.itvedant.movies.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Theater {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    private String name;
    private String location;
    private Integer totalSeats;
    private Integer availableSeats;
    
    private boolean approved = false; // Default: Not Approved
    
    @ManyToMany(mappedBy = "theaters")
    @JsonBackReference
    private List<Movie> movies = new ArrayList<>();
    
    
    @OneToMany(mappedBy = "theater")
    @JsonManagedReference
    private List<Seat> seats;

    @OneToMany(mappedBy = "theater")
    @JsonManagedReference
    private List<ShowTime> showTimes;
    
    @OneToOne
    @JoinColumn(name = "admin_id")
    private User admin;  // Theater Admin linked to this theater

    
    
    
    
	public List<Movie> getMovies() {
		return movies;
	}




	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}




	public boolean isApproved() {
		return approved;
	}




	public void setApproved(boolean approved) {
		this.approved = approved;
	}




	public User getAdmin() {
		return admin;
	}




	public void setAdmin(User admin) {
		this.admin = admin;
	}




	public List<ShowTime> getShowTimes() {
		return showTimes;
	}




	public void setShowTimes(List<ShowTime> showTimes) {
		this.showTimes = showTimes;
	}







	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	public List<Seat> getSeats() {
		return seats;
	}


	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
    
    
}
