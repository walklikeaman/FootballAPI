package com.example.demo.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TableDto {
    int position;
    String name;
    int won;
    int draw;
    int lost;
    int points;
}
