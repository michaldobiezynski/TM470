package com.tm470.WoodMacPark.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "weeklybookings")
public class WeeklyBooking {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "userid")
    private int userId;

    @Column(name = "bookedthisweek")
    private boolean BookedThisWeek;


    public WeeklyBooking() {
    }

    public WeeklyBooking(int id, int userId, boolean bookedThisWeek) {
        this.id = id;
        this.userId = userId;
        BookedThisWeek = bookedThisWeek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isBookedThisWeek() {
        return BookedThisWeek;
    }

    public void setBookedThisWeek(boolean bookedThisWeek) {
        BookedThisWeek = bookedThisWeek;
    }
}
