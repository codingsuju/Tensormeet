package com.example.Tensormeet.Model;

import javax.persistence.*;

@Entity
@Table
@TableGenerator(name="tab", initialValue=0, allocationSize=50)
public class Profile {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.TABLE, generator="tab")
    private Integer id;
    @Column
    private String username;
    @Column
    private String profilename;
    @Column
    private String bio;
    @Column
    private String interest;

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

    public String getProfilename() {
        return profilename;
    }

    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}