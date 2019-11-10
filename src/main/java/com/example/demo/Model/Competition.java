package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    int id;
    Area area;
    String name;
    String code;
    String emblemUrl;
    String plan;
    CurrentSeason currentSeason;
    int numberOfAvailableSeasons;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    LocalDateTime lastUpdated;
}
