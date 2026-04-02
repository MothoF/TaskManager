package com.Mothof.TaskManager.Models;

import jakarta.persistence.*;

@Entity
public class Concepts {
    @EmbeddedId
    private ConceptsId id;
    @ManyToOne
    @MapsId("topicName")
    @JoinColumn(name = "topicname")
    private Topics topic;

    @Column(name = "name", insertable = false, updatable = false)
    private String name;

    public Concepts(){}

    public Concepts(String name, Topics topic){
        this.name = name;
        this.topic = topic;
        this.id = new ConceptsId(name, topic.getTopicName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Topics getTopic() {
        return topic;
    }

    public void setTopic(Topics topic) {
        this.topic = topic;
    }
}
