package com.example.sell.data.model;

import org.omg.IOP.Codec;

import javax.persistence.*;

@Entity(name = "dbo_admin")
public class Admin {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_admin")
    private int idAdmin;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
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
