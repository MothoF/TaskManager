package com.Mothof.TaskManager.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class Users {
    @Id
    @Size(max=50, message = "Username must be less than 50 characters")
    @Column(name = "username")
    private String userName;
    @Size(min=8, message = "Password must be 8 characters long at least and 20 characters long at most")
    @Column(name = "password")
    private String password;
    @Column(name = "gender")
    private String gender;
    @Size(max=20, message = "First name must be less than 20 characters")
    @Column(name = "firstname")
    private String firstName;
    @Size(max=20, message = "Last name must be less than 20 characters")
    @Column(name = "lastname")
    private String lastName;
    @Size(min = 10, max = 10)
    @Column(name = "cellphone")
    private String cellPhone;
    @OneToMany(mappedBy = "user")
    private Set<UsersTasks> allTasksByThisUser;

    public Users() {}

    public Users(String username, String password){
        this.userName = username;
        this.password = password;
    }

    public Users(String username, String password, String cellphone){
        this(username, password);
        this.cellPhone = cellphone;
    }

    public Users(String username, String password, String gender, String firstname, String lastname, String cellphone){
        this(username, password, cellphone);
        this.gender = gender;
        this.firstName = firstname;
        this.lastName = lastname;
    }

    // Getters and setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public void setCellPhone(String cellphone) {
        this.cellPhone = cellphone;
    }

    public String getCellPhone() {
        return cellPhone;
    }
}
