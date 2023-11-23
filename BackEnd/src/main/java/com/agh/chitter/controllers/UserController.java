package com.agh.chitter.controllers;

import com.agh.chitter.model.User;
import com.agh.chitter.requests.SignInRequest;
import com.agh.chitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/getUser")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/signedInUser")
    public ResponseEntity<Object> getUserData(@RequestBody SignInRequest signInRequest) {
        String email = signInRequest.getEmail();

        // Validate credentials
        User user = userService.getUserByEmail(email);
        if (user != null) {
            // Return user data
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/signIn")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequest signInRequest) {
        String email = signInRequest.getEmail();
        String password = signInRequest.getPassword();

        // Validate credentials
        User user = userService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            // Sign-in successful
            return ResponseEntity.status(HttpStatus.OK).body("Sign-in successful");
        } else {
            // Invalid credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody User newUser) {
        String username = newUser.getUsername();
        String email = newUser.getEmail();

        // Check if a user with the same username or email already exists
        if (userService.getUserByUsername(username) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        if (userService.getUserByEmail(email) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        // If no duplicate, proceed with saving the new user
        User savedUser = userService.addUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
