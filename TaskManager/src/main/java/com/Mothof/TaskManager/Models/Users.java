package com.Mothof.TaskManager.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(max=50, message = "Username must be less than 50 characters")
    @NotBlank
    private String username;
    @Size(min=8, message = "Password must be 8 characters long at least and 20 characters long at most")
    @NotBlank
    private String password;
    @NotBlank
    private String gender;
    @Size(max=20, message = "First name must be less than 20 characters")
    @NotBlank
    private String firstname;
    @Size(max=20, message = "Last name must be less than 20 characters")
    @NotBlank
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

    @Override
    public boolean equals(Object o) {
        if (o == null){
            return false;
        }
        Users userObject = (Users) o;
        return username.equals(userObject.username);
    }
}
