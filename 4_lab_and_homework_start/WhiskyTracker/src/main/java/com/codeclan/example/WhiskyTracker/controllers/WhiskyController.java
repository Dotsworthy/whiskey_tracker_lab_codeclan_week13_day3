package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/whiskies")
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping
    public ResponseEntity<List<Whisky>> getAllWhiskeys(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "distillery", required = false) String distillery,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "region", required = false) String region
            ) {
        if (year != null) {
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        if (region != null) {
            return new ResponseEntity<>(whiskyRepository.findByRegion(region), HttpStatus.OK);
        }
        if (age != null) {
            return new ResponseEntity<>(whiskyRepository.findByAge(age), HttpStatus.OK);
        }
        if (distillery != null) {
            return new ResponseEntity<>(whiskyRepository.findByDistillery(distillery), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }
}
