package com.projectmanager;

import com.projectmanager.data.dao.ProjectRepository;
import com.projectmanager.data.dao.TeamMemberRepository;
import com.projectmanager.data.object.Project;
import com.projectmanager.data.object.TeamMember;
import com.projectmanager.service.ProjectService;
import com.projectmanager.service.TeamMemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectManagerApplicationTests {

	@Autowired
	ProjectService projectService;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	TeamMemberService teamMemberService;

	@Autowired
	TeamMemberRepository teamMemberRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void tester() {

		TeamMember teamMember = new TeamMember();
		teamMember.setProjectId("5db0ab2116ec1a23b85393b0");
		teamMember.setName("Tanner Poole");
		teamMember.setRole("Support Technician");
		teamMemberRepository.insert(teamMember);

	}



}
