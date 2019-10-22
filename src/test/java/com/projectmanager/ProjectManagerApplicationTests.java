package com.projectmanager;

import com.projectmanager.data.dao.ProjectRepository;
import com.projectmanager.data.object.Project;
import com.projectmanager.service.ProjectService;
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

	@Test
	public void contextLoads() {
	}

	@Test
	public void tester() {

		String id = "5dad1b653c7c4a15e6e03261";
		Project project = projectService.getProjectByName("egg project");

		System.out.println("PROJECT IS " + project.getName());
	}

}
