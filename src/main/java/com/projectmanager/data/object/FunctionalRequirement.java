package com.projectmanager.data.object;

import org.springframework.data.annotation.Id;


public class FunctionalRequirement {

    @Id
    public String id;

    public String requirement;

    public String projectId;

    public FunctionalRequirement() {}

    public FunctionalRequirement(String requirement, String projectId) {
        this.requirement = requirement;
        this.projectId = projectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


}
