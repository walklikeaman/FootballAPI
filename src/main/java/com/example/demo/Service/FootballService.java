package com.example.demo.Service;


import com.example.demo.DTO.TableDto;
import com.example.demo.Exception.NoInformationException;

import java.util.List;

public interface FootballService {

    List<TableDto> getCompetitionTable(String country, String name) throws NoInformationException;
}