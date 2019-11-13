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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @PostMapping("/delete-risk")
    public String deleteRisk(HttpServletRequest request) {

        String id = request.getParameter("risk");
        String projectId = request.getParameter("projectId");

        riskService.deleteRisk(id);

        return "redirect:/view-project?id=" + projectId;
    }
    @PostMapping("/edit-risk")
    public String editRisk(HttpServletRequest request) {

        String id = request.getParameter("riskId");
        String projectId = request.getParameter("projectId");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        riskService.updateRisk(status, description, id);

        return "redirect:/view-project?id=" + projectId;
    }


    @PostMapping("/create-team-member")
    public String createTeamMember(HttpServletRequest request) {

        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String projectId = request.getParameter("projectId");

        teamMemberService.createTeamMember(name, role, projectId);

        return "redirect:/view-project?id=" + projectId;
    }

    @PostMapping("/delete-team-member")
    public String deleteTeamMember(HttpServletRequest request) {

        String id = request.getParameter("member");
        String projectId = request.getParameter("projectId");

        teamMemberService.deleteTeamMember(id);

        return "redirect:/view-project?id=" + projectId;
    }

    @PostMapping("/edit-team-member")
    public String editTeamMember(HttpServletRequest request) {

        String id = request.getParameter("teamMemberId");
        String projectId = request.getParameter("projectId");
        String name = request.getParameter("name");
        String role = request.getParameter("role");

        teamMemberService.updateTeamMember(name, role, id);

        return "redirect:/view-project?id=" + projectId;
    }

    @PostMapping("/create-functional-requirement")
    public String createFunctionalRequirement(HttpServletRequest request) {

        String requirement = request.getParameter("functionalRequirement");
        String projectId = request.getParameter("projectId");

        requirementService.createFunctionalRequirement(requirement, projectId);

        return "redirect:/view-project?id=" + projectId;
    }

    @PostMapping("/delete-functional-requirement")
    public String deleteFunctionalRequirement(HttpServletRequest request) {

        String id = request.getParameter("functionalRequirementId");
        String projectId = request.getParameter("projectId");

        requirementService.deleteFunctionalRequirement(id);

        return "redirect:/view-project?id=" + projectId;
    }

    @PostMapping("/edit-functional-requirement")
    public String editFunctionalRequirement(HttpServletRequest request) {

        String id = request.getParameter("requirementId");
        String projectId = request.getParameter("projectId");
        String requirement = request.getParameter("functionalRequirement");

        requirementService.updateFunctionalRequirement(requirement, id);

        return "redirect:/view-project?id=" + projectId;
    }

    @PostMapping("/create-non-functional-requirement")
    public String createNonFunctionalRequirement(HttpServletRequest request) {

        String requirement = request.getParameter("nonFunctionalRequirement");
        String projectId = request.getParameter("projectId");

        requirementService.createNonFunctionalRequirement(requirement, projectId);

        return "redirect:/view-project?id=" + projectId;
    }

    @PostMapping("/delete-non-functional-requirement")
    public String deleteNonFunctionalRequirement(HttpServletRequest request) {

        String id = request.getParameter("nonFunctionalRequirementId");
        String projectId = request.getParameter("projectId");

        requirementService.deleteNonFunctionalRequirement(id);

        return "redirect:/view-project?id=" + projectId;
    }

    @PostMapping("/edit-non-functional-requirement")
    public String editNonFunctionalRequirement(HttpServletRequest request) {

        String id = request.getParameter("requirementId");
        String projectId = request.getParameter("projectId");
        String requirement = request.getParameter("nonFunctionalRequirement");

        requirementService.updateNonFunctionalRequirement(requirement, id);

        return "redirect:/view-project?id=" + projectId;
    }

    @PostMapping("update-effort")
    public String updateEffort(HttpServletRequest request) throws ParseException {

        String projectId = request.getParameter("projectId");
        String stringDate = request.getParameter("date");
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
        int requirementsAnalysis = Integer.parseInt(request.getParameter("requirementsAnalysis"));
        int designing = Integer.parseInt(request.getParameter("designing"));
        int coding = Integer.parseInt(request.getParameter("coding"));
        int testing = Integer.parseInt(request.getParameter("testing"));
        int projectManagement = Integer.parseInt(request.getParameter("projectManagement"));

       // String effortId = request.getParameter("id");

        Effort effort;

       // if(effortId == null) {
            effort = new Effort();
       // } else {
       //     effort = effortService.getEffortById(effortId);
       // }

        effort.setProjectId(projectId);
        effort.setDate(date);
        effort.setRequirementAnalysisHours(requirementsAnalysis);
        effort.setDesigningHours(designing);
        effort.setCodingHours(coding);
        effort.setTestingHours(testing);
        effort.setProjectManagementHours(projectManagement);

        effortService.updateEffort(effort);

        return "redirect:/view-project?id=" + projectId;
    }




}
