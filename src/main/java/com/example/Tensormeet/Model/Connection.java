package com.example.Tensormeet.Model;

import javax.persistence.*;

@Entity
@Table
@TableGenerator(name="tab", initialValue=0, allocationSize=50)
public class Connection {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.TABLE, generator="tab")
    private Integer id;
    @Column
    private String username1;
    @Column
    private String username2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

}
