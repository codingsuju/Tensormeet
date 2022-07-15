package com.example.Tensormeet.Model;
import javax.persistence.*;

@Entity
@Table
@TableGenerator(name="tab", initialValue=0, allocationSize=50)
public class AppUser {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.TABLE, generator="tab")
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String role;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
