package com.projectmanager.service;

import com.projectmanager.data.dao.ProjectRepository;
import com.projectmanager.data.object.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project getProjectByName(String name) {
        return projectRepository.findByName(name);
    }

    public Project getProjectById(String id) {
        return projectRepository.findProjectById(id);
    }


    public void createProject(Project project) {
        projectRepository.insert(project);
    }

    public void createProject(String name) {
        Project project = new Project();
        project.setName(name);
        projectRepository.insert(project);
    }

    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }

}
