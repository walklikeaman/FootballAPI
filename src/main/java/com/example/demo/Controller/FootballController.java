package com.example.demo.Controller;

import com.example.demo.DTO.TableDto;
import com.example.demo.Exception.NoInformationException;
import com.example.demo.Service.FootballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FootballController {
    @Autowired
    FootballService footballService;

    @GetMapping(path = "/standings/{country}{name}")
    public ResponseEntity getCompetition(@PathVariable String country, @RequestParam String name) {
        try {
            return ResponseEntity.ok(footballService.getCompetitionTable(country, name));
        } catch (NoInformationException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}

