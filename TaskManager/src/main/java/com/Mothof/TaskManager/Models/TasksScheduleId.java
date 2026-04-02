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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TasksScheduleId)) return false;
        TasksScheduleId that = (TasksScheduleId) o;
        return taskId == that.taskId &&
                scheduleId == that.scheduleId;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(scheduleId, taskId);
    }
}
