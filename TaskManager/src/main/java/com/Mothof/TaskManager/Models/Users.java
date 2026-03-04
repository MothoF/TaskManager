package com.Mothof.TaskManager.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Users {
    @Id
    private int id;
    @Size(max=50, message = "Username must be less than 50 characters")
    private String username;
    @Size(min=8, max=8, message = "Password must be 8 characters long")
    private String password;
    private String gender;
    @Size(max=20, message = "First name must be less than 20 characters")
    private String firstname;
    @Size(max=20, message = "Last name must be less than 20 characters")
    private String lastname;

    public Users() {}

    public Users(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Users(int id, String username, String password){
        this(username, password);
        this.id = id;
    }

    // Getters and setters

    public int getId(){
        return id;
    }

    public void setId(int id) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
