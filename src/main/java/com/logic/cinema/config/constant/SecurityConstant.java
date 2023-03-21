package com.logic.cinema.config.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SecurityConstant {
    API_PATH_TO_MOVIES("api/movies"),
    API_PATH_TO_TICKETS("/api/tickets"),
    API_PATH_TO_HALLS("/api/halls"),
    ALL_API_PATH("/api/**");

    private final String name;

}
