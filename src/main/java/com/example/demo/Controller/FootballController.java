package com.example.demo.Controller;

import com.example.demo.DTO.TableDto;
import com.example.demo.Service.FootballService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TableDto> getCompetition(@PathVariable String country, @RequestParam String name){
        return footballService.getCompetitionTable(country, name);
    }

//    @GetMapping(path = "/competitions/{country}{name}")
//    public int getCompetitionByCountry(@PathVariable String country, @RequestParam String name){
//        return  footballService.getCompetitionByCountry(country, name);
//    }



}

