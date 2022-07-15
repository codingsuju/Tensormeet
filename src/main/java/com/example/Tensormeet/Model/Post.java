package com.example.Tensormeet.Model;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
@Table
@TableGenerator(name="tab", initialValue=0, allocationSize=50)
public class Post {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.TABLE, generator="tab")
    private Integer id;
    @Column
    private String username;
    @Column
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
