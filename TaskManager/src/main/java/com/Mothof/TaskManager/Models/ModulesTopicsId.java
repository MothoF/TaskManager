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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModulesTopicsId)) return false;
        ModulesTopicsId that = (ModulesTopicsId) o;
        return moduleName.equals(that.moduleName) &&
                topicName.equals(that.topicName);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(moduleName, topicName);
    }
}
