package com.Mothof.TaskManager.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(max=20, message = "Module name must be less than 20 characters")
    @Column(name = "modulename")
    private String moduleName;
    @OneToMany(mappedBy = "tasks")
    private Set<UsersTasks> allUsersWithThisTask;

    public Tasks(){}

    public Tasks(String moduleName){
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getId() {
        return id;
    }
}
