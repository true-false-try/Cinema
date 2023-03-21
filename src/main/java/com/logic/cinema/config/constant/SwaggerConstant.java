package com.logic.cinema.config.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SwaggerConstant {
    TITLE("Cinema APIs"),
    DESCRIPTION("CRUD operation for Cinema API"),
    VERSION("1.0");

    private final String name;

}
