package com.projectmanager.controllers;

import com.projectmanager.data.dao.ProjectRepository;
import com.projectmanager.data.object.Project;
import com.projectmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        return "redirect:/dashboard";






    }


}
