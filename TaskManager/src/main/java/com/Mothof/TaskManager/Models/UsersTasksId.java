package com.Mothof.TaskManager.Models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UsersTasksId implements Serializable {
    private String usersEmail;
    private int tasksId;

    public UsersTasksId(){}

    public UsersTasksId(String usersEmail, int tasksId){
        this.usersEmail = usersEmail;
        this.tasksId = tasksId;
    }
}
