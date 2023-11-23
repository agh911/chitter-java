package com.agh.chitter.services;

import com.agh.chitter.model.Peep;
import com.agh.chitter.repositories.PeepRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeepService {
    @Autowired
    private PeepRepository peepRepository;

    public List<Peep> getAllPeeps() {
        return peepRepository.findAllByOrderByCreatedAtDesc();
    }

    public Peep addPeep(Peep peep) {
        return peepRepository.save(peep);
    }

    public Peep getPeepById(String id) {
        return peepRepository.findById(id).orElse(null);
    }

    public void deletePeep(String id) {
        peepRepository.deleteById(id);
    }
}
