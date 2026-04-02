package com.Mothof.TaskManager.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class ModulesTopics {
    @EmbeddedId
    private ModulesTopicsId id;

    @ManyToOne
    @MapsId("moduleName")
    @JoinColumn(name = "modulename")
    private Modules modules;
    @ManyToOne
    @MapsId("topicName")
    @JoinColumn(name = "topicname")
    private Topics topics;

    public ModulesTopics(){}

    public ModulesTopics(Modules modules, Topics topics) {
        this.modules = modules;
        this.topics = topics;
        this.id = new ModulesTopicsId(modules.getModuleName(), topics.getTopicName());
    }

    public Modules getModules() {
        return modules;
    }

    public Topics getTopics() {
        return topics;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }

    public void setTopics(Topics topics) {
        this.topics = topics;
    }
}
