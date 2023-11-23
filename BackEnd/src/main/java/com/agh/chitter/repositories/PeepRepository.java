package com.agh.chitter.repositories;

import com.agh.chitter.model.Peep;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PeepRepository extends MongoRepository<Peep, String> {
    List<Peep> findAllByOrderByCreatedAtDesc();
}
