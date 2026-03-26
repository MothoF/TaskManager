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
}
