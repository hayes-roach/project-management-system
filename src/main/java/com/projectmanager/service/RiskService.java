package com.projectmanager.service;

import com.projectmanager.data.dao.RiskRepository;
import com.projectmanager.data.object.Risk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskService {

    @Autowired
    RiskRepository riskRepository;

    public List<Risk> getAllRisksByProjectId(String projectId) {
        return riskRepository.findAllByProjectId(projectId);
    }

    public void createRisk(String description, String status, String projectId) {

        Risk risk = new Risk(description, status, projectId);
        riskRepository.save(risk);

    }

    public void deleteRisk(String id) {
        riskRepository.deleteById(id);
    }

    public void updateRisk(String status, String description, String id) {

        Risk risk = riskRepository.findRiskById(id);
        risk.setStatus(status);
        risk.setDescription(description);

        riskRepository.save(risk);
    }
}
