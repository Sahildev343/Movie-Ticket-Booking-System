package com.itvedant.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import com.itvedant.movies.Role;
import com.itvedant.movies.dao.LoginDAO;
import com.itvedant.movies.dao.RegisterUserDAO;
import com.itvedant.movies.dao.UpdateUserDAO;
import com.itvedant.movies.entity.Theater;
import com.itvedant.movies.entity.User;
import com.itvedant.movies.repository.TheaterRepository;
import com.itvedant.movies.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    
    
    @Autowired
    private TheaterRepository theaterRepository;
    
 


    
public User register(RegisterUserDAO signUpDAO) {
		
		User user = new User();
		user.setName(signUpDAO.getName());
		user.setMobile_no(signUpDAO.getMobile_no());
		user.setEmail(signUpDAO.getEmail());
		user.setPassword(signUpDAO.getPassword());
		user.setRole(Role.USER);
		
		this.userRepository.save(user);
		return user;
		}


//public String SignIn(String e , String p) {
//	
//	List<User> li = this.userRepository.findAll();
//	for (User user : li) {  
//	    if (e.equals(user.getEmail()) && p.equals(user.getPassword())) {  
//	    	 
//	        return "SignIn"; 
//	    }  
//	}  
//	return "Failed";  
//
//}


public User authenticateUser(String email, String password) {
    // Fetch user from the database and verify password
	List<User> li = this.userRepository.findAll();
	for (User user : li) {  
	    if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {  
	    	 
	        return user; 
	    }  
	}  
    return null;
}



public User update(UpdateUserDAO updateUserDAO, Integer userId) {
	
	User user = this.userRepository.findById(userId).orElseThrow(()-> {
		
		throw new RuntimeException("User Record with this id not found");
	});
	
	if(updateUserDAO.getName() != null) {
		
		user.setName(updateUserDAO.getName());
	}
	
	if(updateUserDAO.getMobile_no() != null) {
		
		user.setMobile_no(updateUserDAO.getMobile_no());
		
	}
	
	if(updateUserDAO.getEmail() != null) {
		
		user.setEmail(updateUserDAO.getEmail());
		
	}
	

	
	
	
	
	
	
	
	this.userRepository.save(user);
	
	return user;
	
}


public User getUserById(Integer userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
}



}
