package com.projectmanager.data.object;

import org.springframework.data.annotation.Id;

public class Project {

    @Id
    public String id;

    public String name;

    public String description;

    public String manager;

    public Project() {}

    public Project(String name, String description, String manager) {
        this.name = name;
        this.description = description;
        this.manager = manager;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

}
