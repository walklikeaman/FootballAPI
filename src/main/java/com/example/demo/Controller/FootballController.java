package com.example.demo.Controller;

import com.example.demo.Model.CompetitionsResponce;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class FootballController {
    @GetMapping(path = "/competitions")
    public CompetitionsResponce getCompetition() {
        CompetitionsResponce competitionsResponce = new CompetitionsResponce();
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.football-data.org/v2/competitions/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", "9d3a8ddfead74213a836bd4444be9554");
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, builder.build().toUri());
        ResponseEntity<CompetitionsResponce> response = restTemplate.exchange(request, CompetitionsResponce.class);
        competitionsResponce = response.getBody();
        return competitionsResponce;
    }
}
