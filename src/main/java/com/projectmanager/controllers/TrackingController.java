package com.projectmanager.controllers;

import com.projectmanager.data.dao.EffortRepository;
import com.projectmanager.data.object.Effort;
import com.projectmanager.service.EffortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TrackingController {

    @Autowired
    EffortService effortService;

    @Autowired
    EffortRepository effortRepository;

    @PostMapping("add-effort")
    public String addEffort(HttpServletRequest request) throws ParseException {

        String projectId = request.getParameter("projectId");
        String stringDate = request.getParameter("date");
        Date date = new SimpleDateFormat("MM/dd/yyyy").parse(stringDate);
        int requirementsAnalysis = Integer.parseInt(request.getParameter("requirementsAnalysis"));
        int designing = Integer.parseInt(request.getParameter("designing"));
        int coding = Integer.parseInt(request.getParameter("coding"));
        int testing = Integer.parseInt(request.getParameter("testing"));
        int projectManagement = Integer.parseInt(request.getParameter("projectManagement"));

        Effort effort = new Effort();

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

    @PostMapping("update-effort")
    public String updateEffort(HttpServletRequest request) throws ParseException {

        String projectId = request.getParameter("projectId");
        int requirementsAnalysis = Integer.parseInt(request.getParameter("requirementsAnalysis"));
        int designing = Integer.parseInt(request.getParameter("designing"));
        int coding = Integer.parseInt(request.getParameter("coding"));
        int testing = Integer.parseInt(request.getParameter("testing"));
        int projectManagement = Integer.parseInt(request.getParameter("projectManagement"));
        String effortId = request.getParameter("effortId");

        Effort effort = effortService.getEffortById(effortId);

        effort.setRequirementAnalysisHours(requirementsAnalysis);
        effort.setDesigningHours(designing);
        effort.setCodingHours(coding);
        effort.setTestingHours(testing);
        effort.setProjectManagementHours(projectManagement);

        effortService.updateEffort(effort);

        return "redirect:/view-project?id=" + projectId;
    }

    @PostMapping("/delete-effort")
    public String deleteEffort(HttpServletRequest request) {

        String id = request.getParameter("effortId");
        String projectId = request.getParameter("projectId");

        effortService.deleteEffortById(id);

        return "redirect:/view-project?id=" + projectId;
    }

}
