package com.projectmanager.data.object;

import org.springframework.data.annotation.Id;

import java.util.Date;


public class Effort {

    @Id
    private String id;
    private String projectId;
    private int requirementAnalysisHours;
    private int designingHours;
    private int codingHours;
    private int testingHours;
    private int projectManagementHours;
    private Date date;

    public Effort(String id, String projectId) {
        this.id = id;
        this.projectId = projectId;
    }

    public Effort() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRequirementAnalysisHours() {
        return requirementAnalysisHours;
    }

    public void setRequirementAnalysisHours(int requirementAnalysisHours) {
        this.requirementAnalysisHours = requirementAnalysisHours;
    }

    public int getDesigningHours() {
        return designingHours;
    }

    public void setDesigningHours(int designingHours) {
        this.designingHours = designingHours;
    }

    public int getCodingHours() {
        return codingHours;
    }

    public void setCodingHours(int codingHours) {
        this.codingHours = codingHours;
    }

    public int getTestingHours() {
        return testingHours;
    }

    public void setTestingHours(int testingHours) {
        this.testingHours = testingHours;
    }

    public int getProjectManagementHours() {
        return projectManagementHours;
    }

    public void setProjectManagementHours(int projectManagementHours) {
        this.projectManagementHours = projectManagementHours;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

}
