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
}
