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
    @Column(name = "topicname")
    private String topicName;
    @Column(name = "conceptname")
    private String conceptName;
    @OneToMany(mappedBy = "tasks")
    private Set<UsersTasks> allUsersWithThisTask;

    public Tasks(){}

    public Tasks(String moduleName, String topicName, String conceptName){
        this.moduleName = moduleName;
        this.topicName = topicName;
        this.conceptName = conceptName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getConceptName() {
        return conceptName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setConceptName(String conceptName) {
        this.conceptName = conceptName;
    }
}
