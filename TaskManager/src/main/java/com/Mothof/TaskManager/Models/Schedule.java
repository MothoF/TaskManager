package com.Mothof.TaskManager.Models;

import jakarta.persistence.*;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int scheduleId;

    public Schedule(){}

    public int getScheduleId() {
        return scheduleId;
    }
}
