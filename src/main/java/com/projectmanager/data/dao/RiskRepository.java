package com.projectmanager.data.dao;

import com.projectmanager.data.object.Risk;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskRepository extends MongoRepository<Risk, String> {

    public List<Risk> findAllByProjectId(String projectId);

    public Risk findRiskById(String id);

}
