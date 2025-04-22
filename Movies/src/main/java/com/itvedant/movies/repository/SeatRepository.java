package com.itvedant.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itvedant.movies.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

	List<Seat> findByTheaterId(Integer theaterId);

	List<Seat> findAllById(Integer seatIds);
}
