package com.projectmanager.controllers;

import com.projectmanager.data.dao.FunctionalReqRepository;
import com.projectmanager.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RequirementsController {

    @Autowired
    FunctionalReqRepository functionalReqRepository;

    @Autowired
    RequirementService requirementService;

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

}
