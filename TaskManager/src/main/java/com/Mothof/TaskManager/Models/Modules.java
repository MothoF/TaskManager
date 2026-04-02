package com.Mothof.TaskManager.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import java.util.Set;
import jakarta.persistence.Column;


@Entity
public class Modules {
    @Id
    @Size(max=20, message = "Module name must be less than 20 characters")
    @Column(name = "name")
    private String moduleName;
    @OneToMany(mappedBy = "modules")
    private Set<ModulesTopics> allTopicsInThisModule;

    public Modules(){}

    public Modules(String name){
        this.moduleName = name;
    }

    public String getModuleName(){
        return moduleName;
    }

    public void setModuleName(String name){
        this.moduleName = name;
    }
}
