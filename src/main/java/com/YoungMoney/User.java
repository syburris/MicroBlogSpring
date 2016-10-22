package com.YoungMoney;

import javax.persistence.*;


/**
 * Created by stevenburris on 10/22/16.
 */
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue
    int id;

    @Column
    String name;

    @Column
    String password;

    public User() {
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
