package com.Mothof.TaskManager.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class Topics {
    @Id
    @Size(max=20, message = "Topic name must be less than 20 characters")
    private String name;
    @OneToMany(mappedBy = "topics")
    private Set<ModulesTopics> allModulesWithThisTopic;

    public Topics(){}

    public Topics(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
