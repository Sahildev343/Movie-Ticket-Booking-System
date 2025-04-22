	package com.itvedant.movies.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itvedant.movies.dao.AddShowTimesDAO;
import com.itvedant.movies.entity.ShowTime;
import com.itvedant.movies.repository.ShowTimeRespository;
import com.itvedant.movies.service.ShowTimeService;

@Controller
@RequestMapping("/showtimes")
public class ShowTimeController {

	
	@Autowired
	private ShowTimeService showTimeService;
	
	
	@Autowired
	private ShowTimeRespository showTimeRespository;
	
	
	@GetMapping
	public ResponseEntity<List<ShowTime>> getShowTimesByTheaterAndDate(
	        @RequestParam Integer theaterId,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

	    List<ShowTime> showTimes = showTimeService.getShowTimesByTheaterAndDate(theaterId, date); // [UPDATED]
	    return ResponseEntity.ok(showTimes);
	}
	
	
	@GetMapping("/{showTimeId}")
	public ResponseEntity<?> getById(@PathVariable Integer showTimeId) {
		
		return ResponseEntity.ok(this.showTimeService.getById(showTimeId));
		
	}

	
	@PostMapping
	public ResponseEntity<?> createShowTime(@RequestBody AddShowTimesDAO addShowTimesDAO) {
	    try {
	        int numberOfDays = 7; //Add this to create showtimes for next 7 days
	        ShowTime createdShowTime = showTimeService.create(addShowTimesDAO, numberOfDays);
	        return ResponseEntity.ok(createdShowTime);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}

	
	
	@GetMapping("/theater/{theaterId}")
	public ResponseEntity<?> findByTheaterId(@PathVariable Integer theaterId) {
		
		return ResponseEntity.ok(this.showTimeService.getShowTimeByTheaterId(theaterId));
	}
	
	

	
	@PostMapping("/{theaterId}")
	public List<ShowTime> getShowTimes(
	        @PathVariable Integer theaterId,
	        @RequestBody Map<String, String> request) {
	    
	    String selectedDate = request.get("selectedDate");
	    LocalDate parsedDate = LocalDate.parse(selectedDate);
	    
	    return showTimeService.getShowTimesByTheaterAndDate(theaterId, parsedDate);
	}


	@PutMapping("/assign-showtimes-to-theater/{theaterId}")
	public ResponseEntity<String> assignShowTimesToTheater(@PathVariable Integer theaterId) {
	    showTimeService.assignDefaultShowTimesToTheater(theaterId);
	    return ResponseEntity.ok("Showtimes assigned successfully to Theater ID: " + theaterId);
	}


}
