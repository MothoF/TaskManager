package com.Mothof.TaskManager.Models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ConceptsId implements Serializable {
    private String name;
    private String topicName;

    public ConceptsId(){}

    public ConceptsId(String name, String topicName){
        this.name = name;
        this.topicName = topicName;
    }
}
