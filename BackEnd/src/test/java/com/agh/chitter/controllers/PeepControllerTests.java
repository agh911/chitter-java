package com.agh.chitter.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.agh.chitter.model.Peep;
import com.agh.chitter.model.User;
import com.agh.chitter.requests.PeepRequest;
import com.agh.chitter.services.PeepService;

public class PeepControllerTests {
    @Mock
    private PeepService peepService;

    @InjectMocks
    private PeepController peepController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPeeps() {
        // Arrange
        List<Peep> peeps = Arrays.asList(
                new Peep("John Doe", "john_doe", "Hello, Chitter!"),
                new Peep("Jane Smith", "jane_smith", "Chitter is awesome!"));
        when(peepService.getAllPeeps()).thenReturn(peeps);

        // Act
        List<Peep> result = peepController.getAllPeeps();

        // Assert
        assertEquals(peeps, result);
        verify(peepService, times(1)).getAllPeeps();
    }

    @Test
    void testAddPeep() {
        // Arrange
        User newUser = new User("New User", "new_user", "new_user@example.com", "password123");
        Peep newPeep = new Peep("New User", "new_user", "My first peep!");
        PeepRequest peepRequest = new PeepRequest(newUser, newPeep);

        when(peepService.addPeep(newPeep)).thenReturn(newPeep);

        // Act
        ResponseEntity<Peep> responseEntity = peepController.addPeep(peepRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(newPeep, responseEntity.getBody());
        verify(peepService, times(1)).addPeep(newPeep);
    }

    @Test
    void testDeletePeep() {
        // Arrange
        Peep newPeep = new Peep("New User", "new_user", "My first peep!");
        String peepId = newPeep.getId();

        // Act
        peepController.deletePeep(peepId);

        // Assert
        verify(peepService, times(1)).deletePeep(peepId);
    }
}
