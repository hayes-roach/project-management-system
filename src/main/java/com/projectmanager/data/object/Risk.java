package com.projectmanager.data.object;

import org.springframework.data.annotation.Id;

public class Risk {

    @Id
    public String id;

    public String description;

    public String status;

    public String projectId;

    public Risk(String description, String status, String projectId) {
        this.description = description;
        this.status = status;
        this.projectId = projectId;
    }

    public Risk() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
