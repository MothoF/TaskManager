package com.Mothof.TaskManager.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;

import java.util.Set;

@Entity
public class Topics {
    @Id
    @Size(max=20, message = "Topic name must be less than 20 characters")
    @Column(name="name")
    private String topicName;
    @OneToMany(mappedBy = "topics")
    private Set<ModulesTopics> allModulesWithThisTopic;

    public Topics(){}

    public Topics(String name){
        this.topicName = name;
    }

    public String getTopicName(){
        return topicName;
    }

    public void setTopicName(String name){
        this.topicName = name;
    }
}
