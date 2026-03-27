package com.Mothof.TaskManager.Models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class TasksScheduleId implements Serializable {
    private int taskId;
    private int scheduleId;

    public TasksScheduleId(){}

    public TasksScheduleId(int taskId, int scheduleId){
        this.taskId = taskId;
        this.scheduleId = scheduleId;
    }
}
