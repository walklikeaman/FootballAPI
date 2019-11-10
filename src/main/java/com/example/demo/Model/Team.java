package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    int id;
    Area area;
    String name;
    String shortName;
    String tla;
    String crestUrl;
    String address;
    String phone;
    String website;
    String email;
    int founded;
    String clubColors;
    String venue;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    LocalDateTime lastUpdated;
}
