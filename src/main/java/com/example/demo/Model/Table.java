package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Table {
    int position;
    Team team;
    int playedGames;
    int won;
    int lost;
    int points;
    int goalsFor;
    int goalsAgainst;
    int goalDifference;
}
