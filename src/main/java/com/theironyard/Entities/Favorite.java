package com.theironyard.Entities;

import javax.persistence.*;

/**
 * Created by branden on 3/14/16 at 11:31.
 */
@Entity
public class Favorite {

    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    User user;

    @ManyToOne
    Event event;

    public Favorite() {
    }

    public Favorite(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}