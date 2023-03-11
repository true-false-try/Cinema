package com.logic.cinema.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "login", length = 128, nullable = false)
    private String login;

    @Column(name = "password",length = 128, nullable = false)
    private String password;

    @Column(name = "age", length = 3)
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Ticket> tickets;

}
