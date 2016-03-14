package com.theironyard.Entities;

import javax.persistence.*;

/**
 * Created by branden on 3/14/16 at 11:09.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;


    public User(String name) {
        this.name = name;
    }

    public User() {
    }
}