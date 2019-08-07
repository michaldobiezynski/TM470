package com.tm470.WoodMacPark.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "bookingsfortoday")
public class BookingForToday {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "space")
    private int space;

    @Column(name = "user")
    private int user;


    public BookingForToday() {
    }

    public BookingForToday(int id, int space, int user) {
        this.id = id;
        this.space = space;
        this.user = user;
    }


    public int getId() {
        return id;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getUser() {
        return user;
    }


}


