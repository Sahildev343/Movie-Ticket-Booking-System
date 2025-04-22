package com.itvedant.movies.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itvedant.movies.entity.Booking;
import com.itvedant.movies.entity.ShowTime;
import com.itvedant.movies.entity.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    // ✅ Find booked seats for a specific showtime
	@Query("SELECT b.bookedSeats FROM Booking b WHERE b.showtime.id = :showTimeId")
    List<List<String>> findBookedSeatsByShowtime(@Param("showTimeId") Integer showTimeId);

    // ✅ Find booked seats for a specific theater & showtime
    @Query("SELECT b.bookedSeats FROM Booking b WHERE b.showtime.theater.id = :theaterId AND b.showtime.id = :showtimeId")
    List<List<String>> findBookedSeats(@Param("theaterId") Integer theaterId, @Param("showtimeId") Integer showtimeId);

    // ✅ Find all bookings for a specific showtime
    List<Booking> findByShowtime(ShowTime showtime);  

    // ✅ Find all bookings using only showtimeId
    List<Booking> findByShowtimeId(Integer showtimeId);
    
    
    List<Booking> findByUserEmail(String userEmail);
    
    

    List<Booking> findByUser(User user);
    


    
    

    
    

}
