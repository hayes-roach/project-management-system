package com.projectmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ProjectManagerApplication extends Throwable {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagerApplication.class, args);
	}


}
