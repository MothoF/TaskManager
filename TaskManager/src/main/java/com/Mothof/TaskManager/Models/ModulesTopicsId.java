package com.Mothof.TaskManager.Models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ModulesTopicsId implements Serializable {
    private String moduleName;
    private String topicName;

    public ModulesTopicsId(){}

    public ModulesTopicsId(String moduleName, String topicName) {
        this.moduleName = moduleName;
        this.topicName = topicName;
    }
}
