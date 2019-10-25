package com.projectmanager.controllers;

import com.projectmanager.data.dao.ProjectRepository;
import com.projectmanager.data.dao.RiskRepository;
import com.projectmanager.data.object.Project;
import com.projectmanager.data.object.Risk;
import com.projectmanager.service.ProjectService;
import com.projectmanager.service.RiskService;
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

    @Autowired
    RiskService riskService;

    @Autowired
    RiskRepository riskRepository;

    @GetMapping("/")
    public String dashboard(Model model, HttpSession httpSession) {

        List<Project> projects = projectRepository.findAll();

        model.addAttribute("projects", projects);

        return "dashboard";
    }

    @PostMapping("/create-project")
    public String createProject(HttpServletRequest request) {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String manager = request.getParameter("manager");



        Project project = projectService.createProject(name, description, manager);

        String id = project.getId();

        return "redirect:/view-project?id=" + id;
    }

    @GetMapping("/view-project")
    public String viewProject(Model model, @RequestParam String id) {

        List<Project> projects = projectRepository.findAll();

        model.addAttribute("projects", projects);

        Project project = projectService.getProjectById(id);


        model.addAttribute("project", project);

        List<Risk> risks = riskService.getAllRisksByProjectId(id);

        model.addAttribute("risks", risks);

        return "view-project";
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

    @GetMapping("/update-project-manager")
    public String updateProjectManager(HttpServletRequest request) {

        String id = request.getParameter("projectId");
        String manager = request.getParameter("projectmanager");

        Project project = projectService.getProjectById(id);
        project.setManager(manager);

        projectRepository.save(project);


        return "redirect:/";
    }

    @PostMapping("/create-risk")
    public String createRisk(HttpServletRequest request) {

       String description = request.getParameter("description");
       String status = request.getParameter("status");
       String projectId = request.getParameter("projectId");

       riskService.createRisk(description, status, projectId);

        return "redirect:/view-project?id=" + projectId;
    }




}
