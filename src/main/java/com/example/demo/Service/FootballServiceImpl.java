package com.example.demo.Service;

import com.example.demo.DTO.TableDto;
import com.example.demo.Exception.NoInformationException;
import com.example.demo.Model.CompetitionsResponce;
import com.example.demo.Model.StandingsResponce;
import com.example.demo.Model.Table;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballServiceImpl implements FootballService {
    HashMap<String, List<TableDto>> tableMap = new HashMap<>();

    public List<TableDto> getCompetitionTable(String country, String name) throws NoInformationException {
        int id = 0;
        try {
            id = getCompetitionByCountryAndName(country, name);
        } catch (RestClientException e) {
            if (tableMap.get(country + name) == null) {
                throw new NoInformationException();
            }
            return tableMap.get(country + name);
        }
        String url = "https://api.football-data.org/v2/competitions/" + id + "/standings?standingType=HOME";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<StandingsResponce> response = restTemplate.exchange(sendRequestToProvider(url), StandingsResponce.class);
        StandingsResponce standingsResponce = response.getBody();
        List<Table> tables = standingsResponce.getStandings().get(0).getTable();
        List<TableDto> tableDtoList = tables.stream()
                .map(e -> convertTableToTableDto(e))
                .collect(Collectors.toList());
        tableMap.put(country + name, tableDtoList);
        return tableDtoList;
    }

    public int getCompetitionByCountryAndName(String country, String name) throws RestClientException {
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
                .position(table.getPosition())
                .name(table.getTeam().getName())
                .won(table.getWon())
                .draw(table.getDraw())
                .lost(table.getLost())
                .points(table.getPoints())
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