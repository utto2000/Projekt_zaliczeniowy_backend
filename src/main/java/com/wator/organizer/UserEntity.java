package com.wator.organizer;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "first_name", nullable = false)
    private String FirstName;
    @Column(name = "second_name", nullable = false)
    private String secondName;
    @Column(name = "Email", nullable = false)
    private String email;
    @Column(name = "user_password", nullable = false)
    private String password;

    public UserEntity(){

    }
    public UserEntity(int id, String firstName, String secondName, String email, String password) {
        this.id = id;
        this.FirstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}