package com.projectmanager.controllers;

import com.projectmanager.data.dao.ProjectRepository;
import com.projectmanager.data.object.Project;
import com.projectmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/")
    public String dashboard(Model model, HttpSession httpSession) {

        List<Project> projects = projectRepository.findAll();

        model.addAttribute("projects", projects);

        return "dashboard";
    }

    @PostMapping("/create-project")
    public String createProject(@RequestParam String name) {

        projectService.createProject(name);

        return "redirect:/";
    }

    @GetMapping("/view-project")
    public String viewProject(Model model, @RequestParam String id) {

        List<Project> projects = projectRepository.findAll();

        model.addAttribute("projects", projects);

        Project project = projectService.getProjectById(id);


        model.addAttribute("project", project);

        return "view-license";
    }

    @PostMapping("/delete-project")
    public String deleteProject(@ModelAttribute Project project) {

        String id = project.getId();

        projectService.deleteProject(id);

        return "redirect:/";
    }

    @GetMapping("/update-description")
    public String updateProjectDescription(HttpServletRequest request) {

        String id = request.getParameter("projectId");
        String description = request.getParameter("description");

        Project project = projectService.getProjectById(id);
        project.setDescription(description);

        projectRepository.save(project);


        return "redirect:/";
    }


}
