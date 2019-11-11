package com.example.demo.Service;

import com.example.demo.DTO.TableDto;
import com.example.demo.Model.CompetitionsResponce;
import com.example.demo.Model.StandingsResponce;
import com.example.demo.Model.Table;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FootballService {

    public List<TableDto> getCompetitionTable(String country, String name) {
        int id = getCompetitionByCountry(country, name);
        String url = "https://api.football-data.org/v2/competitions/" + id + "/standings?standingType=HOME";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<StandingsResponce> response = restTemplate.exchange(sendRequestToProvider(url), StandingsResponce.class);
        StandingsResponce standingsResponce = response.getBody();
        List<Table> tables = standingsResponce.getStandings().get(0).getTable();
        List<TableDto> tableDtoList = tables.stream()
                .map(e -> convertTableToTableDto(e))
                .collect(Collectors.toList());
        return tableDtoList;


    }
    
    public int getCompetitionByCountry(String country, String name) {
        String url = "https://api.football-data.org/v2/competitions";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CompetitionsResponce> response = restTemplate.exchange(sendRequestToProvider(url), CompetitionsResponce.class);
        CompetitionsResponce competitionsResponce = response.getBody();
        int countryId = Arrays.stream(competitionsResponce.getCompetitions())
                .filter(e -> e.getArea().getName().trim().toLowerCase().equals(country.toLowerCase().trim()))
                .filter(e -> e.getName().toLowerCase().equals(name.toLowerCase().trim()))
                .map(e -> e.getId())
                .findFirst().orElse(-1);
        return countryId;
    }

    private TableDto convertTableToTableDto(Table table) {
        return TableDto.builder()
                .draw(table.getDraw())
                .lost(table.getLost())
                .name(table.getTeam().getName())
                .points(table.getPoints())
                .position(table.getPosition())
                .won(table.getWon())
                .build();
    }

    private RequestEntity<String> sendRequestToProvider(String url) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", "9d3a8ddfead74213a836bd4444be9554");
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, builder.build().toUri());
        return request;
    }
}