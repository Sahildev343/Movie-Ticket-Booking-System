package com.itvedant.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.itvedant.movies.entity.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
	public Optional<User> findByEmail(String email);
	
	


	
	@RestResource(exported=true)
	<S extends User> S save(S entity);
} 
