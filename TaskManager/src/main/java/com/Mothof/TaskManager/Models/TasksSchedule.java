package com.Mothof.TaskManager.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;

import java.sql.Time;
import java.util.Date;

@Entity
public class TasksSchedule {
    @EmbeddedId
    private TasksScheduleId id;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "taskid")
    private Tasks task;
    @ManyToOne
    @MapsId("scheduleId")
    @JoinColumn(name = "scheduleid")
    private Schedule schedule;

    @FutureOrPresent
    @Column(name = "scheduleddate")
    private Date date;
    @FutureOrPresent
    @Column(name = "scheduledtime")
    private Time time;

    public TasksSchedule(){}

    public TasksSchedule(Date date, Time time){
        this.date = date;
        this.time = time;
    }

    public TasksSchedule(Tasks task, Schedule schedule, Date date, Time time){
        this(date, time);
        this.task = task;
        this.schedule = schedule;
    }

    public Tasks getTask() {
        return task;
    }

    public void setTask(Tasks task) {
        this.task = task;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
