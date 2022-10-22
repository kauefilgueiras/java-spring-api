package com.example.usersapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "active")
    private boolean active;
    public User(){

    }
    public User(String name, String email, String phone, boolean active){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.active = active;
    }
}
