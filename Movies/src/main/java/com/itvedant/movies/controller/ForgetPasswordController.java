package com.itvedant.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.itvedant.movies.dao.ChangePasswordDAO;
import com.itvedant.movies.dao.CheckOtpDAO;
import com.itvedant.movies.dao.SendMailDAO;
import com.itvedant.movies.service.ForgetPasswordService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@SessionAttributes("email")
@Controller
@RequestMapping("/users")
public class ForgetPasswordController {

	@Autowired
	private ForgetPasswordService passwordService;
	
	
	
	@PostMapping("/email")
	public ResponseEntity<?> send(@RequestBody SendMailDAO sendMailDAO, HttpSession session) {
		
		session.setAttribute("email", sendMailDAO.getEmail());
		
		return ResponseEntity.ok(this.passwordService.SendEmail(sendMailDAO, session));
	}
	
	@PostMapping("/check")
	public ResponseEntity<?> check(@RequestBody CheckOtpDAO checkOtpDAO, HttpSession session) {
	    Integer sessionOtp = (Integer) session.getAttribute("otp"); // Retrieve OTP from session

	    // Debugging: Print stored OTP and user-entered OTP
	    System.out.println("Stored OTP in session: " + sessionOtp);
	    System.out.println("User entered OTP: " + checkOtpDAO.getOtp());

	    if (sessionOtp == null || !sessionOtp.equals(checkOtpDAO.getOtp())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP Does Not Match");
	    }

	    // Store email in session after OTP verification
	    session.setAttribute("email", checkOtpDAO.getEmail());
	    return ResponseEntity.ok("OTP Verified");
	}

	
	@PutMapping("/changePassword")
	public ResponseEntity<?> changePassword(HttpSession session, @RequestBody ChangePasswordDAO changePasswordDAO) {
	    String email = (String) session.getAttribute("email");

	    // Debugging: Print email from session
	    System.out.println("Session Email: " + email);

	    if (email == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired. Please verify OTP again.");
	    }

	    return ResponseEntity.ok(this.passwordService.updatePassword(email, changePasswordDAO));
	}


	
	
}
