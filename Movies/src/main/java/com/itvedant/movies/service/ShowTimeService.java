package com.itvedant.movies.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.itvedant.movies.dao.AddShowTimesDAO;
import com.itvedant.movies.entity.Movie;
import com.itvedant.movies.entity.ShowTime;
import com.itvedant.movies.entity.Theater;
import com.itvedant.movies.repository.MoviesRepository;
import com.itvedant.movies.repository.ShowTimeRespository;
import com.itvedant.movies.repository.TheaterRepository;

@Service
public class ShowTimeService {

	
	@Autowired
	private ShowTimeRespository showTimeRespository;
	
	
	@Autowired
	private TheaterRepository theaterRepository;
	
	
	@Autowired
	private MoviesRepository moviesRepository;


	private LocalDate date;
	
	
	
	public ShowTime create(AddShowTimesDAO addShowTimesDAO, Integer numberOfDays) {
		
		Theater theater = this.theaterRepository.findById(addShowTimesDAO.getTheaterId()).orElseThrow(()-> {
			throw new RuntimeException("Theater With this ID not Found");
		});
		
		Movie movie = this.moviesRepository.findById(addShowTimesDAO.getMovieId()).orElseThrow(()-> {
			throw new RuntimeException("Movie with This Id is Not Found");
		});
		
		
		
		  LocalDate today = LocalDate.now();
		  ShowTime lastSavedShowTime = null; // to return last saved object

		    for (int i = 0; i < numberOfDays; i++) {
		        this.date = today.plusDays(i);
		        
		        boolean exists = this.showTimeRespository.existsByTheaterAndDateAndTime(theater, this.date, addShowTimesDAO.getTime());
		        System.out.println("Checking date: " + this.date + ", time: " + addShowTimesDAO.getTime() + ", exists: " + exists);

		        
		    
		if(!exists) {
			  ShowTime showTime = new ShowTime();
		showTime.setTime(addShowTimesDAO.getTime());
		showTime.setDate(date);
		showTime.setTheater(theater);
		showTime.setMovie(movie);
		
		
		lastSavedShowTime  = this.showTimeRespository.save(showTime);
		  System.out.println("Saved showtime for date: " + date + ", time: " + addShowTimesDAO.getTime());
    	 
    	
		}
		    
		    }
			return lastSavedShowTime; 
		   
		 
	}
	
	
	
		
	
	public List<ShowTime> getShowTimeByTheaterId(Integer theaterId) {
		
		return this.showTimeRespository.findByTheaterId(theaterId);
	}
	
	public List<ShowTime> getShowTimesByTheaterAndDate(Integer theaterId, LocalDate selectedDate) {
	    List<ShowTime> showTimes = this.showTimeRespository.findByTheaterIdAndDate(theaterId, selectedDate);
	    LocalDate today = LocalDate.now();
	    LocalTime currentTime = LocalTime.now();

	    if (selectedDate.equals(today)) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH);
	        return showTimes.stream()
	            .filter(showTime -> {
	                String cleanedTime = showTime.getTime().toUpperCase().trim().replaceAll("\\s+", " ");
	                LocalTime parsedTime = LocalTime.parse(cleanedTime, formatter);
	                return parsedTime.isAfter(currentTime);
	            })
	            .collect(Collectors.toList());
	    } else {
	        return showTimes;
	    }
	}
	
	
	
	public ShowTime getById(Integer showTimeId) {
		
		ShowTime showTime = this.showTimeRespository.findById(showTimeId).orElse(null);
		
		
		return showTime;
	}



	
	
	public List<ShowTime> getShowtime() {
		
		List<ShowTime> li = this.showTimeRespository.findAll();
		
		return li;
	}
	
	
	
	
	
	
	
	
	// assign to all
	public String assignDefaultShowTimesToTheater(Integer theaterId) {
	    // Fetch the theater by ID
	    Theater theater = theaterRepository.findById(theaterId)
	            .orElseThrow(() -> new RuntimeException("Theater not found with ID: " + theaterId));

	    // Example list of default times
	    List<String> defaultTimes = Arrays.asList("10:00", "13:00", "16:00", "19:00");
	    LocalDate today = LocalDate.now();

	    // Loop through each default time and assign it to the ShowTime
	    for (String time : defaultTimes) {
	        List<Movie> movies = theater.getMovies(); // Assuming a @ManyToMany or @OneToMany relation
	        if (movies.isEmpty()) {
	            throw new RuntimeException("No movies associated with this theater.");
	        }


	        Movie movie = movies.get(0);  // Select the first movie

	        // Create a new ShowTime entity
	        ShowTime showTime = new ShowTime();
	        showTime.setTime(time);
	        showTime.setDate(today);
	        showTime.setTheater(theater);
	        showTime.setMovie(movie); // Assign the movie to the showtime
	        
	        // Save the showtime to the repository
	        showTimeRespository.save(showTime);
	    }

	    return "Default showtimes assigned to theater ID: " + theaterId + " for " + today;
	}
	
	
	//schedule
	 @Scheduled(cron = "0 0 0 * * *") // Runs every day at midnight
	    public void updateOldShowDates() {
	        LocalDate today = LocalDate.now();
	        LocalDate yesterday = today.minusDays(1);
	        LocalDate tomorrow = today.plusDays(1);

	        // Fetch showtimes with yesterday's date
	        List<ShowTime> oldShowTimes = this.showTimeRespository.findByDate(yesterday);

	        for (ShowTime showTime : oldShowTimes) {
	            showTime.setDate(tomorrow); // Set to tomorrow
	        }

	        this.showTimeRespository.saveAll(oldShowTimes);

	        System.out.println("Updated " + oldShowTimes.size() + " showtime entries from " + yesterday + " to " + tomorrow);
	    }







	
	

}

