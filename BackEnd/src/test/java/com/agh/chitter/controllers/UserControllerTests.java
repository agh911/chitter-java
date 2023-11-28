package com.agh.chitter.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.agh.chitter.model.User;
import com.agh.chitter.requests.SignInRequest;
import com.agh.chitter.services.UserService;

public class UserControllerTests {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        // Arrange
        List<User> userList = new ArrayList<>();

        when(userService.getAllUsers()).thenReturn(userList);

        // Act
        List<User> result = userController.getAllUsers();

        // Assert
        assertEquals(userList, result);
    }

    @Test
    void getUserData() {
        // Arrange
        String userEmail = "test@example.com";
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail(userEmail);

        User user = new User();
        user.setName("Test User");
        user.setUsername("testuser");
        user.setEmail(userEmail);
        user.setPassword("password123");

        when(userService.getUserByEmail(userEmail)).thenReturn(user);

        // Act
        ResponseEntity<Object> responseEntity = userController.getUserData(signInRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    void getUserDataUserNotFound() {
        // Arrange
        String userEmail = "nonexistent@example.com";
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail(userEmail);

        // Mock the userService method calls and expected behavior
        when(userService.getUserByEmail(userEmail)).thenReturn(null);

        // Act
        ResponseEntity<Object> responseEntity = userController.getUserData(signInRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid credentials", responseEntity.getBody());
    }

    @Test
    void getUserById() {
        // Arrange
        User expectedUser = new User();
        expectedUser.setName("Test User");
        expectedUser.setUsername("testuser");
        expectedUser.setEmail("test@example.com");
        expectedUser.setPassword("password123");
        String userId = expectedUser.getId();

        when(userService.getUserById(userId)).thenReturn(expectedUser);

        // Act
        User result = userController.getUserById(userId);

        // Assert
        assertEquals(expectedUser, result);
    }

    @Test
    void signInUserNotFound() {
        // Arrange
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail("nonexistent@example.com");

        // Mock the userService method calls and expected behavior
        when(userService.getUserByEmail(signInRequest.getEmail())).thenReturn(null);

        // Act
        ResponseEntity<Object> responseEntity = userController.signIn(signInRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid credentials", responseEntity.getBody());
    }

    @Test
    void signInSuccess() {
        // Arrange
        String userEmail = "test@example.com";
        String userPassword = "password123";

        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail(userEmail);
        signInRequest.setPassword(userPassword);

        User mockUser = new User();
        mockUser.setPassword(userPassword);

        when(userService.getUserByEmail(userEmail)).thenReturn(mockUser);

        // Act
        ResponseEntity<Object> responseEntity = userController.signIn(signInRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Sign-in successful", responseEntity.getBody());
    }

    @Test
    void signUpNotSuccessful() {
        // Arrange
        User newUser = new User();
        String username = "testuser";
        String userEmail = "test@example.com";

        newUser.setUsername(username);
        newUser.setEmail(userEmail);

        when(userService.getUserByUsername(username)).thenReturn(newUser);

        // Act
        ResponseEntity<Object> responseEntity = userController.signUp(newUser);

        // Assert
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("Username already exists", responseEntity.getBody());
    }

    @Test
    void signUpSuccess() {
        // Arrange
        User newUser = new User();
        String username = "testuser";
        String userEmail = "test@example.com";

        newUser.setUsername(username);
        newUser.setEmail(userEmail);

        when(userService.getUserByUsername(username)).thenReturn(null);
        when(userService.getUserByEmail(userEmail)).thenReturn(null);
        when(userService.addUser(newUser)).thenReturn(newUser);

        // Act
        ResponseEntity<Object> responseEntity = userController.signUp(newUser);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(newUser, responseEntity.getBody());
    }

    @Test
    void signUpEmailAlreadyExists() {
        // Arrange
        User newUser = new User("User One", "userone", "user1@email.com", "pass1");
        User anotherNewUser = new User("User Two", "usertwo", "user1@email.com", "newpass");

        when(userService.getUserByUsername(newUser.getUsername())).thenReturn(null);
        when(userService.getUserByEmail(newUser.getEmail())).thenReturn(anotherNewUser);

        // Act
        ResponseEntity<Object> responseEntity = userController.signUp(newUser);

        // Assert
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("Email already exists", responseEntity.getBody());
    }

    @Test
    void deleteUser() {
        // Arrange
        User newUser = new User();
        newUser.setName("Test User");
        newUser.setUsername("testuser");
        newUser.setEmail("test@example.com");
        newUser.setPassword("password123");
        String userId = newUser.getId();

        // Act
        userController.deleteUser(userId);

        // Assert
        verify(userService, times(1)).deleteUser(userId);
    }
}
