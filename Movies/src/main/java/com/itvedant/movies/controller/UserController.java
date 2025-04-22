package com.itvedant.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.itvedant.movies.dao.LoginDAO;
import com.itvedant.movies.dao.RegisterUserDAO;
import com.itvedant.movies.dao.UpdateUserDAO;
import com.itvedant.movies.dao.UserResponse;
import com.itvedant.movies.entity.User;
import com.itvedant.movies.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register User
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDAO registerUserDAO) {
    	
               User newUser = userService.register(registerUserDAO);  // register the user
               
               // Return user details after successful registration
               return ResponseEntity.ok(new UserResponse(newUser.getId(), newUser.getEmail()));
          
              
           
       }
    

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam String email, @RequestParam String password) {
    	  User user = userService.authenticateUser(email, password);
          
          if (user != null) {
              // Return userId and email upon successful login
              return ResponseEntity.ok(new UserResponse(user.getId(), user.getEmail()));
          } else {
              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to sign in");
          }
       
    }
    
    
    
    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@RequestBody UpdateUserDAO updateUserDAO, @PathVariable Integer userId) {
    	
    	return ResponseEntity.ok(this.userService.update(updateUserDAO, userId));
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    
   
}

