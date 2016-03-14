package com.theironyard.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by branden on 3/14/16 at 10:48.
 */
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    LocalDateTime dateTime;

    @ManyToOne
    User user;


    public Event(String description, LocalDateTime dateTime, User user) {
        this.description = description;
        this.dateTime = dateTime;
        this.user = user;
    }

    public Event() {
    }
}