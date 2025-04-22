package com.itvedant.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itvedant.movies.entity.Movie;
import com.itvedant.movies.entity.Theater;





@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer> {

	@Query("SELECT m FROM Movie m JOIN m.theaters t WHERE t = :theater")
    List<Movie> findByTheater(@Param("theater") Theater theater);

}
