package com.tm470.WoodMacPark.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Bookings")
public class Booking {

    @Id
    @Column(name = "idbookings")
    private int id;

    @Column(name = "space")
    private int space;

    @Column(name = "user")
    private int user;


    public Booking() {
    }

    public Booking(int id, int space, int user) {
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


