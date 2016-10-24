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

    @Column(nullable = false)
    String text;

    @ManyToOne
    User user;

    @Transient
    boolean isMe;

    public Message() {
    }

    public Message(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Message(int id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
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

}
