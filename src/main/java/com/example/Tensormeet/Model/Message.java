package com.example.Tensormeet.Model;

import javax.persistence.*;

@Entity
@Table
@TableGenerator(name="tab", initialValue=0, allocationSize=50)
public class Message {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.TABLE, generator="tab")
    private Integer id;
    @Column
    private String sender;
    @Column
    private String receiver;
    @Column
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}