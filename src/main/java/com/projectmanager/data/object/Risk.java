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
}
