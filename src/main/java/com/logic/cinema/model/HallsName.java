package com.logic.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HallsName {
    WHITE("white"),
    BLACK("black"),
    RED("red"),
    BLUE("blue"),
    YELLOW("yellow"),
    ORANGE("orange"),
    PINK("pink");

    private final String name;

}
