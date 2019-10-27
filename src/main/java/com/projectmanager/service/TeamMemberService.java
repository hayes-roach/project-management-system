package com.projectmanager.service;

import com.projectmanager.data.dao.TeamMemberRepository;
import com.projectmanager.data.object.TeamMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberService {

    @Autowired
    TeamMemberRepository teamMemberRepository;

    public List<TeamMember> getAllTeamMembersByProjectId(String projectId) {
        return teamMemberRepository.findAllByProjectId(projectId);
    }

    public void createTeamMember(String name, String role, String projectId) {

        TeamMember teamMember = new TeamMember(name, role, projectId);
        teamMemberRepository.save(teamMember);

    }

    public void deleteTeamMember(String id) {
        teamMemberRepository.deleteById(id);
    }

}

