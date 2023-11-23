package com.agh.chitter.controllers;

import com.agh.chitter.model.Peep;
// import com.agh.chitter.model.User;
// import com.agh.chitter.requests.PeepRequest;
import com.agh.chitter.services.PeepService;
// import com.agh.chitter.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/peeps")
@CrossOrigin(origins = "*")
public class PeepController {
    @Autowired
    private PeepService peepService;

    // @Autowired
    // private UserService userService;

    @GetMapping
    public List<Peep> getAllPeeps() {
        return peepService.getAllPeeps();
    }

    @PostMapping
    public Peep addPeep(@RequestBody Peep newPeep) {
        if (newPeep.getDate() == null) {
            newPeep.setDate(LocalDateTime.now().toString());
        }
        return peepService.addPeep(newPeep);
    }

    @DeleteMapping("/{id}")
    public void deletePeep(@PathVariable String id) {
        peepService.deletePeep(id);
    }
}
