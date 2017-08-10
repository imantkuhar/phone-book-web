package com.cooksdev.data.entity;

import com.cooksdev.data.enums.UserState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "USERS")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy="user")
    private List<Contact> contacts;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private UserState state;

    public String getOAuthRole() {
       return state.name();
    }
}
