package com.agh.chitter.controllers;

import com.agh.chitter.model.Peep;
import com.agh.chitter.services.PeepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peeps")
@CrossOrigin(origins = "*")
public class PeepController {
    @Autowired
    private PeepService peepService;

    @GetMapping
    public List<Peep> getAllPeeps() {
        return peepService.getAllPeeps();
    }

    @PostMapping
    public Peep addPeep(@RequestBody Peep newPeep) {
        return peepService.addPeep(newPeep);
    }

    @DeleteMapping("/{id}")
    public void deletePeep(@PathVariable String id) {
        peepService.deletePeep(id);
    }
}
