package com.Mothof.TaskManager.Models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UsersTasksId implements Serializable {
    private String userName;
    private int taskId;

    public UsersTasksId(){}

    public UsersTasksId(String userName, int taskId){
        this.userName = userName;
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersTasksId)) return false;
        UsersTasksId that = (UsersTasksId) o;
        return taskId == that.taskId &&
                userName.equals(that.userName);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(userName, taskId);
    }
}
