package com.agh.chitter.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agh.chitter.model.User;
import com.agh.chitter.repositories.UserRepository;

public class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        // Arrange
        User user1 = new User("User1", "user1", "user1@example.com", "password1");
        User user2 = new User("User2", "user2", "user2@example.com", "password2");
        List<User> expectedUsers = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertEquals(expectedUsers, result);
    }

    @Test
    void addUser() {
        // Arrange
        User newUser = new User("New User", "newuser", "newuser@example.com", "newpassword");

        when(userRepository.save(newUser)).thenReturn(newUser);

        // Act
        User result = userService.addUser(newUser);

        // Assert
        assertEquals(newUser, result);
    }

    @Test
    void getUserById() {
        // Arrange
        User expectedUser = new User("Test User", "testuser", "testuser@example.com", "password123");
        String userId = expectedUser.getId();

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertEquals(expectedUser, result);
    }

    @Test
    void getUserByEmail() {
        // Arrange
        String userEmail = "testuser@example.com";
        User expectedUser = new User("Test User", "testuser", userEmail, "password123");

        when(userRepository.findByEmail(userEmail)).thenReturn(expectedUser);

        // Act
        User result = userService.getUserByEmail(userEmail);

        // Assert
        assertEquals(expectedUser, result);
    }

    @Test
    void getUserByUsername() {
        // Arrange
        String username = "testuser";
        User expectedUser = new User("Test User", username, "testuser@example.com", "password123");

        when(userRepository.findByUsername(username)).thenReturn(expectedUser);

        // Act
        User result = userService.getUserByUsername(username);

        // Assert
        assertEquals(expectedUser, result);
    }

    @Test
    void deleteUser() {
        // Arrange
        User newUser = new User("New User", "newuser", "newuser@example.com", "password123");
        String userId = newUser.getId();

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }
}
