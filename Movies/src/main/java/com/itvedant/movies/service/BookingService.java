package com.itvedant.movies.service;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itvedant.movies.dao.BookingResponse;
import com.itvedant.movies.dao.MovieDTO;
import com.itvedant.movies.dao.TheaterDTO;
import com.itvedant.movies.entity.Booking;
import com.itvedant.movies.entity.Movie;
import com.itvedant.movies.entity.ShowTime;
import com.itvedant.movies.entity.Theater;
import com.itvedant.movies.entity.User;
import com.itvedant.movies.repository.BookingRepository;
import com.itvedant.movies.repository.MoviesRepository;
import com.itvedant.movies.repository.ShowTimeRespository;
import com.itvedant.movies.repository.TheaterRepository;
import com.itvedant.movies.repository.UserRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowTimeRespository showTimeRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MoviesRepository moviesRepository;
    
    
    @Autowired
    private UserRepository userRepository;


    public BookingResponse bookTicket(Integer showTimeId, Integer userId, List<String> selectedSeats, Integer movieId) {
        // ✅ Fetch showtime from database
        ShowTime showTime = showTimeRepository.findById(showTimeId)
                .orElseThrow(() -> new RuntimeException("ShowTime not found"));
        
        // ✅ Fetch user from database
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Fetch theater and movie information from showtime
        Theater theater = showTime.getTheater();
        
        Movie movie = this.moviesRepository.findById(movieId).orElseThrow(()-> {
        	throw new RuntimeException("Movie not found");
        });

        // ✅ Convert to Set<String> instead of Set<List<String>> for booked seats
        Set<String> bookedSeats = this.bookingRepository.findBookedSeatsByShowtime(showTimeId)
                                                        .stream()
                                                        .flatMap(List::stream)
                                                        .collect(Collectors.toSet());

        // ✅ Check if any selected seat is already booked
        for (String seat : selectedSeats) {
            if (bookedSeats.contains(seat)) {
                throw new RuntimeException("❌ Seat " + seat + " is already booked.");
            }
        }

        // ✅ Save new booking
        Booking newBooking = new Booking();
        newBooking.setUser(user);  // Set the user
        newBooking.setShowtime(showTime);  // Set the selected showtime (which includes the show date and time)
        newBooking.setTheater(theater);  // Set the theater
        newBooking.setMovie(movie);  // Set the movie
        
        newBooking.setBookedSeats(selectedSeats);  // Set the booked seats
        
        // ✅ Set today's date as the booking date
        newBooking.setBookingDate(LocalDate.now());  // Booking Date is today (actual booking date)
        
        // ✅ Set the booking status
        newBooking.setBookingStatus("CONFIRMED");

        // ✅ Save the new booking
        newBooking = bookingRepository.save(newBooking);

        // ✅ Return response including Show Date and Show Time
        return new BookingResponse(
                newBooking.getId(),  // Booking ID
                user.getId(),  // User ID
                new MovieDTO(movie.getId(), movie.getTitle()),  // Movie DTO
                new TheaterDTO(theater.getId(), theater.getName(), theater.getLocation()),  // Theater DTO
                newBooking.getBookedSeats(),  // List of booked seats
                newBooking.getBookingDate(),  // Booking Date (Today)
                newBooking.getBookingStatus(),  // Booking Status
                "✅ Booking successful!",  // Success message
                showTime.getDate(),  // Show Date (From ShowTime)
                showTime.getTime().toString()  // Show Time (From ShowTime)
        );
    }

    

    
    public List<String> getBookedSeatsByShowTimes(Integer showTimeId) {
        List<Booking> bookings = this.bookingRepository.findByShowtimeId(showTimeId);
        List<String> bookedSeats = new ArrayList<>();
        for (Booking booking : bookings) {
            bookedSeats.addAll(booking.getBookedSeats());
        }
        return bookedSeats;
    }
    
    
    
    // Get Booking By Id
    public Booking getBookingById(Integer bookingId) {
        return this.bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
    
    
    public List<BookingResponse> getBookingsByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("❌ User not found with id: " + userId));

        List<Booking> bookings = bookingRepository.findByUser(user);

        return bookings.stream().map(booking -> {
            ShowTime showTime = booking.getShowtime(); // Extract showTime for each booking
            return new BookingResponse(
                booking.getId(),
                user.getId(),
                new MovieDTO(
                    booking.getMovie().getId(),
                    booking.getMovie().getTitle()
                ),
                new TheaterDTO(
                    booking.getTheater().getId(),
                    booking.getTheater().getName(),
                    booking.getTheater().getLocation()
                ),
                booking.getBookedSeats(),
                booking.getBookingDate(),
                booking.getBookingStatus(),
                "✅ Booking fetched successfully!",
                showTime.getDate(),
                showTime.getTime().toString()
            );
        }).collect(Collectors.toList());

    }
    
    
    
   





}

