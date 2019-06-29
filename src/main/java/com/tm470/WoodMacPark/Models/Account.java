package com.tm470.WoodMacPark.Models;

import com.tm470.WoodMacPark.Repositories.AccountIdRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

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


    @Column(name="firstname")
    private String firstname;

    @Column(name="secondname")
    private String secondname;

    @Column(name="email")
    private String email;


    public Account() {}


    public Account(int idUser, String username, String password,
                   String firstname, String secondname, String email,
                   String role, Boolean enabled)
    {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.secondname = secondname;
        this.email = email;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
