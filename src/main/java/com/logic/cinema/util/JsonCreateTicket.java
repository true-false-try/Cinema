package com.logic.cinema.util;

import com.logic.cinema.model.constant.TariffsType;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JsonCreateTicket {
        String movieName;
        String login;
        String password;
        Integer row;
        Integer seat;
        TariffsType type;
        Double cost;

}
