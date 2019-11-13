package com.projectmanager.controllers;

import com.google.gson.Gson;
import com.projectmanager.data.dao.*;
import com.projectmanager.data.object.*;
import com.projectmanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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

    @Autowired
    TeamMemberService teamMemberService;

    @Autowired
    TeamMemberRepository teamMemberRepository;

    @Autowired
    FunctionalReqRepository functionalReqRepository;

    @Autowired
    RequirementService requirementService;

    @Autowired
    EffortService effortService;

    @Autowired
    EffortRepository effortRepository;

    @GetMapping("/")
    public String dashboard(Model model, HttpSession httpSession) {

        List<Project> projects = projectRepository.findAll();

        System.out.println(projects.size());
        model.addAttribute("projects", projects);

        if(projects.size() == 0)
            model.addAttribute("hasNoProjects", true);
        else
            model.addAttribute("hasNoProjects", false);


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
    public String viewProject(HttpServletRequest request, Model model, @RequestParam String id) {

        List<Project> projects = projectRepository.findAll();

        model.addAttribute("projects", projects);

        Project project = projectService.getProjectById(id);

        model.addAttribute("project", project);

        List<Risk> risks = riskService.getAllRisksByProjectId(id);

        model.addAttribute("risks", risks);

        List<TeamMember> teamMembers =  teamMemberService.getAllTeamMembersByProjectId(id);

        model.addAttribute("team", teamMembers);

        List<FunctionalRequirement> functionalRequirements = requirementService.getAllFunctionalRequirementsByProjectId(id);

        model.addAttribute("functionalRequirements", functionalRequirements);

        List<NonFunctionalRequirement> nonFunctionalRequirements = requirementService.getAllNonFunctionalRequirementsByProjectId(id);

        model.addAttribute("nonFunctionalRequirements", nonFunctionalRequirements);

        HttpSession session = request.getSession();

        List<Effort> effortList = effortRepository.findAllByProjectId(id);

        int requirementAnalysisHours = 0;
        int codingHours = 0;
        int testingHours = 0;
        int designingHours = 0;
        int projectManagementHours = 0;

        for(Effort effort : effortList) {
            codingHours = codingHours + effort.getCodingHours();
            testingHours = testingHours + effort.getTestingHours();
            designingHours = designingHours + effort.getDesigningHours();
            projectManagementHours = projectManagementHours + effort.getProjectManagementHours();
            requirementAnalysisHours = requirementAnalysisHours + effort.getRequirementAnalysisHours();
        }

        model.addAttribute("codingTotal", codingHours);
        model.addAttribute("testingTotal", testingHours);
        model.addAttribute("designingTotal", designingHours);
        model.addAttribute("projectMgtTotal", projectManagementHours);
        model.addAttribute("requirementAnalysisTotal", requirementAnalysisHours);

        int totalHours = codingHours + testingHours + designingHours + projectManagementHours + requirementAnalysisHours;

        model.addAttribute("totalHours", totalHours);

        Double codingDistribution = (double) codingHours / totalHours;
        Double testingDistribution = (double) testingHours / totalHours;
        Double designingDistribution = (double) designingHours / totalHours;
        Double projectMgtDistribution = (double) projectManagementHours / totalHours;
        Double requirementAnalysisDistribution = (double) requirementAnalysisHours / totalHours;

        Gson gsonObj = new Gson();
        Map<Object,Object> map = null;
        List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

        map = new HashMap<Object,Object>();
        map.put("label", "Testing");
        map.put("y", testingDistribution * 100);
        list.add(map);

        map = new HashMap<Object,Object>();
        map.put("label", "Coding");
        map.put("y", codingDistribution * 100);
        list.add(map);

        map = new HashMap<Object,Object>();
        map.put("label", "Designing");
        map.put("y", designingDistribution * 100);
        list.add(map);

        map = new HashMap<Object,Object>();
        map.put("label", "Project Management");
        map.put("y", projectMgtDistribution * 100);
        list.add(map);

        map = new HashMap<Object,Object>();
        map.put("label", "Requirement Analysis");
        map.put("y", requirementAnalysisDistribution * 100);
        list.add(map);


        model.addAttribute("dataPoints", list);

        Collections.reverse(effortList);
        model.addAttribute("effortList", effortList);

        return "view-project";
    }

    @PostMapping("/delete-project")
    public String deleteProject(@ModelAttribute Project project) {

        String id = project.getId();
        projectService.deleteProject(id);

        return "redirect:/";
    }
}
