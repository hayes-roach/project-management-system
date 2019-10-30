package com.projectmanager.data.dao;

import com.projectmanager.data.object.TeamMember;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMemberRepository extends MongoRepository<TeamMember, String> {

    public List<TeamMember> findAllByProjectId(String projectId);

    public TeamMember findTeamMemberById(String teamMemberId);

}

