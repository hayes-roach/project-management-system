package com.projectmanager.data.dao;

import com.projectmanager.data.object.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

    public Project findByName(String name);

    public Project findProjectById(String id);




}
