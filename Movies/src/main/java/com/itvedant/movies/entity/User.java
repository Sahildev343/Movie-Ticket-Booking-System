package com.itvedant.movies.entity;

import java.util.Collection;
import java.util.List;

import com.itvedant.movies.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email") )
public class User {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    private String name;
	    private String email;
	    private String password;
	    private Long mobile_no;
	    
	    @Enumerated(EnumType.STRING)
	    private Role role;
	    
	    @OneToOne(mappedBy = "admin")
	    private Theater theater;
	    
	    
	    @OneToMany(mappedBy = "user")
	    private List<Booking> bookings;
	    
	    
	
	
	  
	
	
	public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
	public Long getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(Long mobile_no) {
		this.mobile_no = mobile_no;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setTheater(Theater theater2) {
		// TODO Auto-generated method stub
		
	}
	public Theater getTheater() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
}
