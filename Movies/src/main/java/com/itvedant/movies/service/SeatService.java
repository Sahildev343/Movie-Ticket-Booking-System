package com.itvedant.movies.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itvedant.movies.dao.AddSeatsDAO;
import com.itvedant.movies.entity.Booking;
import com.itvedant.movies.entity.Seat;
import com.itvedant.movies.entity.ShowTime;
import com.itvedant.movies.entity.Theater;
import com.itvedant.movies.repository.BookingRepository;
import com.itvedant.movies.repository.SeatRepository;
import com.itvedant.movies.repository.ShowTimeRespository;
import com.itvedant.movies.repository.TheaterRepository;

@Service
public class SeatService {
	
	
    @Autowired
    private SeatRepository seatRepository;
    
    @Autowired
    private ShowTimeRespository showTimeRepository;
    
    
    @Autowired
    private TheaterRepository theaterRepository;
    
    
    @Autowired
    private BookingRepository bookingRepository;
    
    
    public Seat create(AddSeatsDAO addSeatsDAO) {
    	
    	Theater theater = this.theaterRepository.findById(addSeatsDAO.getTheaterId()).orElseThrow(()-> {
    		throw new RuntimeException("Theater with this Id Not Found");
    	});
    	
    	Booking booking = this.bookingRepository.findById(addSeatsDAO.getBookingId()).orElseThrow(()-> {
    		throw new RuntimeException("Booking is not Found with this Id");
    	});
    	
    			
    	
    	Seat seat = new Seat();
    	seat.setLabel(addSeatsDAO.getLabel());
    	seat.setBooked(addSeatsDAO.isBooked());
    	seat.setTheater(theater);
    	seat.setBooking(booking);
    	
    	this.seatRepository.save(seat);
    	
    	return seat;
    	
    }
    
    public List<Map<String, Object>> getSeatsByShowTime(Integer showTimeId) {
        List<Map<String, Object>> seats = new ArrayList<>();

        // Fetch ShowTime and its associated Theater
        ShowTime showTime = this.showTimeRepository.findById(showTimeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ShowTime not found"));

        Theater theater = showTime.getTheater();
        if (theater == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Theater not found for this ShowTime");
        }

        int totalSeats = theater.getTotalSeats();

        // Fetch booked seats (returns List<List<String>>)
        List<List<String>> bookedSeatsList = bookingRepository.findBookedSeatsByShowtime(showTimeId);

        // Flatten the List<List<String>> into List<String>
        List<String> bookedSeats = bookedSeatsList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        // Generate seat labels dynamically
        for (int i = 0; i < totalSeats; i++) {
            Map<String, Object> seat = new HashMap<>();
            String row = String.valueOf((char) ('A' + (i / 10))); // A, B, C...
            String seatLabel = row + (i % 10 + 1);

            seat.put("seatNumber", seatLabel);
            seat.put("booked", bookedSeats.contains(seatLabel));

            seats.add(seat);
        }

        return seats;
    }

    
    public List<AddSeatsDAO> getSeatsByTheater(Integer theaterId) {
    	return this.seatRepository.findByTheaterId(theaterId)
    			.stream()
    			.map(seat -> new AddSeatsDAO(seat.getId(), seat.getLabel(), seat.isBooked()))
    			.collect(Collectors.toList());
    }

    public List<Seat> getSeats(Integer theaterId) {
        return this.seatRepository.findByTheaterId(theaterId);
    }
    
    
    public Map<String, Object> getSeatAvailability(Integer showTimeId) {
        ShowTime showTime = this.showTimeRepository.findById(showTimeId)
                .orElseThrow(() -> new RuntimeException("ShowTime not found"));

        int totalSeats = showTime.getTheater().getTotalSeats();

        List<Booking> bookings = bookingRepository.findByShowtimeId(showTimeId);
        Set<String> bookedSeats = new HashSet<>();

        for (Booking booking : bookings) {
            bookedSeats.addAll(booking.getBookedSeats());
        }

        int availableSeats = totalSeats - bookedSeats.size();

        Map<String, Object> response = new HashMap<>();
        response.put("totalSeats", totalSeats);
        response.put("availableSeats", availableSeats);
        response.put("bookedSeats", bookedSeats);

        return response;
    }
    
    private List<String> generateAllSeats(int totalSeats) {
        List<String> seats = new ArrayList<>();
        char[] rows = {'A', 'B', 'C', 'D', 'E'}; // Adjust row names as needed
        int seatsPerRow = totalSeats / rows.length;

        for (char row : rows) {
            for (int num = 1; num <= seatsPerRow; num++) {
                seats.add(row + num, null);
            }
        }
        return seats;
    }
    
    public List<String> getAvailableSeats(Integer theaterId, Integer showTimeId) {
        // Fetch booked seats from repository (returns List<List<String>>)
        List<List<String>> bookedSeatsList = this.bookingRepository.findBookedSeats(theaterId, showTimeId);

        // Flatten the List<List<String>> into List<String>
        List<String> bookedSeats = bookedSeatsList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        // Generate all seat numbers dynamically (e.g., A1, A2, ..., E10)
        List<String> allSeats = generateAllSeats();

        // Filter available seats
        return allSeats.stream()
                .filter(seat -> !bookedSeats.contains(seat))
                .collect(Collectors.toList());
    }


    private List<String> generateAllSeats() {
        List<String> seats = new ArrayList<>();
        char[] rows = {'A', 'B', 'C', 'D', 'E'}; // Rows
        for (char row : rows) {
            for (int i = 1; i <= 10; i++) {
                seats.add(row + String.valueOf(i)); // A1, A2, ..., E10
            }
        }
        return seats;
    }

}
