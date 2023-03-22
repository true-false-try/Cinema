package com.logic.cinema.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {
    ALL_READ("all:read"),
    ALL_WRITE("all:write");

    private final String permission;
}
