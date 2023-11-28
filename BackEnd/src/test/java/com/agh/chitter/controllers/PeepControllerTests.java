package com.agh.chitter.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agh.chitter.model.Peep;
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
    void createPeepObjectAndVerifyMethods() {
        // Arrange
        String name = "John Doe";
        String username = "john_doe";
        String content = "Hello, Chitter!";
        LocalDateTime createdAt = LocalDateTime.now();

        Peep peep = new Peep();
        peep.setName(name);
        peep.setUsername(username);
        peep.setContent(content);
        peep.setDate(createdAt);

        // Act
        String actualName = peep.getName();
        String actualUsername = peep.getUsername();
        String actualContent = peep.getContent();
        LocalDateTime actualCreatedAt = peep.getDate();

        // Assert
        assertEquals(name, actualName);
        assertEquals(username, actualUsername);
        assertEquals(content, actualContent);
        assertEquals(createdAt, actualCreatedAt);
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
        Peep newPeep = new Peep("New User", "new_user", "My first chitter!");

        when(peepService.addPeep(newPeep)).thenReturn(newPeep);

        // Act
        Peep result = peepController.addPeep(newPeep);

        // Assert
        assertEquals(newPeep, result);
        verify(peepService, times(1)).addPeep(newPeep);
    }

    @Test
    void testDeletePeep() {
        // Arrange
        String peepId = "123";

        // Act
        peepController.deletePeep(peepId);

        // Assert
        verify(peepService, times(1)).deletePeep(peepId);
    }
}
