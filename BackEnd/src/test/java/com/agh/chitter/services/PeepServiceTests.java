package com.agh.chitter.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.agh.chitter.model.Peep;
import com.agh.chitter.repositories.PeepRepository;

public class PeepServiceTests {
    @Mock
    private PeepRepository peepRepository;

    @InjectMocks
    private PeepService peepService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPeeps() {
        // Arrange
        Peep peep1 = new Peep("User1", "user1", "Content 1");
        Peep peep2 = new Peep("User2", "user2", "Content 2");
        List<Peep> expectedPeeps = Arrays.asList(peep1, peep2);

        when(peepRepository.findAllByOrderByCreatedAtDesc()).thenReturn(expectedPeeps);

        // Act
        List<Peep> result = peepService.getAllPeeps();

        // Assert
        assertEquals(expectedPeeps, result);
    }

    @Test
    void getPeepById() {
        // Arrange
        Peep expectedPeep = new Peep("User1", "user1", "Content 1");
        String peepId = expectedPeep.getId();

        when(peepRepository.findById(peepId)).thenReturn(Optional.of(expectedPeep));

        // Act
        Peep result = peepService.getPeepById(peepId);

        // Assert
        assertEquals(expectedPeep, result);
    }

    @Test
    void addPeep() {
        // Arrange
        Peep newPeep = new Peep("User1", "user1", "Content 1");

        when(peepRepository.save(newPeep)).thenReturn(newPeep);

        // Act
        Peep result = peepService.addPeep(newPeep);

        // Assert
        assertEquals(newPeep, result);

        verify(peepRepository).save(newPeep);
    }

    @Test
    void deletePeep() {
        // Arrange
        Peep newPeep = new Peep("User1", "user1", "Content 1");
        String peepId = newPeep.getId();

        // Act
        peepService.deletePeep(peepId);

        // peep id
        verify(peepRepository).deleteById(peepId);
    }
}