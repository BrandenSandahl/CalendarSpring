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

    @Transient
    boolean showFavButton;


    public Event(String description, LocalDateTime dateTime, User user) {
        this.description = description;
        this.dateTime = dateTime;
        this.user = user;
    }

    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isShowFavButton() {
        return showFavButton;
    }

    public void setShowFavButton(boolean showFavButton) {
        this.showFavButton = showFavButton;
    }
}