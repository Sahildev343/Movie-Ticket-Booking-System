package com.itvedant.movies.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itvedant.movies.dao.ChangePasswordDAO;
import com.itvedant.movies.dao.CheckOtpDAO;
import com.itvedant.movies.dao.SendMailDAO;
import com.itvedant.movies.entity.User;
import com.itvedant.movies.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ForgetPasswordService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	Random random = new Random();
	
	int otp = 0;
	public String SendEmail(SendMailDAO sendMailDAO, HttpSession session) {
	    if (userRepository.findByEmail(sendMailDAO.getEmail()).isPresent()) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        int otp = random.nextInt(100000); // Generate new OTP
	        session.setAttribute("otp", otp); // Store OTP in session

	        message.setFrom("sahiluradi567@gmail.com");
	        message.setTo(sendMailDAO.getEmail());
	        message.setSubject("OTP Verification");
	        message.setText("Your OTP is " + otp);

	        mailSender.send(message);

	        return "Mail Sent";
	    } else {
	        return "Email Not Registered";
	    }
	}

	
	public String checkOtp(CheckOtpDAO checkOtpDAO) {
		
		if(this.otp == checkOtpDAO.getOtp()) {
			return "OTP Verified";
		}else {
			return "OTP Does Not Matched";
		}
	}
	
	public User updatePassword(String email, ChangePasswordDAO changePasswordDAO) {
	    // Fetch user by email
	    User user = this.userRepository.findByEmail(email).orElse(null);

	    // If user does not exist, throw an exception
	    if (user == null) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email);
	    }

	    // Check if new password and confirm password match
	    if (!changePasswordDAO.getNewPassword().equals(changePasswordDAO.getConfirmPassword())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password and Confirm password do not match.");
	    }

	    // Update password
	    user.setPassword(changePasswordDAO.getNewPassword());
	    this.userRepository.save(user);

	    return user;
	}

	

}
