package com.projectmanager.data.dao;

import com.projectmanager.data.object.FunctionalRequirement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionalReqRepository extends MongoRepository<FunctionalRequirement, String> {

    public List<FunctionalRequirement> findAllByProjectId(String projectId);

    public FunctionalRequirement findFunctionalRequirementById(String id);

}