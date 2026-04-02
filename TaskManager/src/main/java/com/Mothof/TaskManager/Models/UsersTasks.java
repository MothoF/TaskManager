package com.Mothof.TaskManager.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class UsersTasks {
    @EmbeddedId
    private UsersTasksId id;

    @ManyToOne
    @MapsId("userName")
    @JoinColumn(name = "username")
    private Users user;
    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "taskid")
    private Tasks tasks;

    @Size(max=7)
    @Column(name = "progress")
    private String progress;
    @Column(name = "priority")
    private String priority;
    @Column(name = "description")
    private String description;

    public UsersTasks(){}

    public UsersTasks(String progress, String priority, String description){
        this.progress = progress;
        this.priority = priority;
        this.description = description;
    }

    public UsersTasks(Users user, Tasks tasks, String progress, String priority, String description){
        this(progress, priority, description);
        this.user = user;
        this.tasks = tasks;
        this.id = new UsersTasksId(user.getUserName(), tasks.getTaskId());
    }

    public String getProgress() {
        return progress;
    }

    public String getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUser() {
        return user;
    }

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
