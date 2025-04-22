package com.itvedant.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itvedant.movies.entity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {

	   @Query("SELECT t FROM Theater t JOIN t.movies m WHERE m.id = :movieId")
	    List<Theater> findByMovieId(@Param("movieId") Integer movieId);

	

}
