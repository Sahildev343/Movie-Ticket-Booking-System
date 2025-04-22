package com.itvedant.movies.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itvedant.movies.entity.ShowTime;
import com.itvedant.movies.entity.Theater;


@Repository
public interface ShowTimeRespository extends JpaRepository<ShowTime, Integer> {
	
	List<ShowTime> findByTheaterId(Integer theaterId);
	
	@Query("SELECT s FROM ShowTime s WHERE s.theater.id = :theaterId AND s.date = :selectedDate")
	List<ShowTime> findByTheaterIdAndDate(@Param("theaterId") Integer theaterId, @Param("selectedDate") LocalDate selectedDate);

	boolean existsByTheaterAndDateAndTime(Theater theater, LocalDate date, String time);

	@Query("SELECT MAX(s.date) FROM ShowTime s WHERE s.theater.id = :theaterId")
	LocalDate findMaxDateByTheater(@Param("theaterId") Integer theaterId);

	List<ShowTime> findByDate(LocalDate selectedDate);

	
	
  

	
	


	
	
	
	

}
