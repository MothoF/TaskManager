package com.Mothof.TaskManager.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Entity
public class Modules {
    @Id
    @Size(max=20, message = "Module name must be less than 20 characters")
    private String name;
    @OneToMany(mappedBy = "modules")
    private Set<ModulesTopics> allTopicsInThisModule;

    public Modules(){}

    public Modules(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
