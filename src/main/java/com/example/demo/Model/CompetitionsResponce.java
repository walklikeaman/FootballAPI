package com.example.demo.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionsResponce {
    int count;
    Filter filters;
    Competition[] competitions;
}
