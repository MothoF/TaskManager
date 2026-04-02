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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConceptsId)) return false;
        ConceptsId that = (ConceptsId) o;
        return name.equals(that.name) &&
                topicName.equals(that.topicName);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, topicName);
    }
}
