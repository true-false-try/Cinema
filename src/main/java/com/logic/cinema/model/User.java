package com.logic.cinema.model;

<<<<<<< HEAD
import javax.persistence.*;
import java.util.List;
=======


import javax.persistence.*;
import java.util.Set;
>>>>>>> origin/testWeb

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Integer id;
=======
    private Long id;
>>>>>>> origin/testWeb

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "login", length = 128, nullable = false)
    private String login;

    @Column(name = "password",length = 128, nullable = false)
    private String password;

    @Column(name = "age", length = 3)
    private Integer age;

<<<<<<< HEAD
    @OneToMany
    private List<Ticket> tickets;
=======
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Ticket> tickets;
>>>>>>> origin/testWeb

    public User() {

    }

<<<<<<< HEAD
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
=======
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
>>>>>>> origin/testWeb
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

<<<<<<< HEAD
    public List<Ticket> getTicket() {
        return tickets;
    }

    public void setTicket(List<Ticket> tickets) {
=======
    public Set<Ticket> getTicket() {
        return tickets;
    }

    public void setTicket(Set<Ticket> tickets) {
>>>>>>> origin/testWeb
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", ticket=" + tickets +
                '}';
    }
}
