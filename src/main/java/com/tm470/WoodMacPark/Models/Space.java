package com.tm470.WoodMacPark.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Spaces")
public class Space {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name="Booked", nullable = false)
    private boolean booked;

    @Column(name="UserID")
    private int userId;

    @Column(name="Fixed", nullable = false)
    private boolean fixed;


    public Space() {}

    public Space(int id, boolean booked, int userId, boolean fixed) {
        this.id = id;
        this.booked = booked;
        this.userId = userId;
        this.fixed = fixed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }
}
