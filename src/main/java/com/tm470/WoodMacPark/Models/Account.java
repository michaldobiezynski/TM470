package com.tm470.WoodMacPark.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class Account {

    @Id
    @Column(name = "iduser")
    private int idUser;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;


    public Account() {}

    public Account(int idUser, String username, String password) {

        this.idUser = idUser;
        this.username = username;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
