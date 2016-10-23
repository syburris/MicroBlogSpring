package com.YoungMoney;

import javax.persistence.*;

/**
 * Created by stevenburris on 10/22/16.
 */
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    int id;

    @Column
    String text;

    @Column
    int userId;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public Message(int id, String text, int userId) {
        this.id = id;
        this.text = text;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
