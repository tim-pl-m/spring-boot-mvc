package com.projektpool.example;

import com.projektpool.example.domain.Project;
import com.projektpool.example.dao.jpa.ProjectRepository;
import com.projektpool.example.service.ProjectService;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * This is the main Spring Boot application class. It configures Spring Boot, JPA, Swagger
 */

@EnableAutoConfiguration  // Sprint Boot Auto Configuration
@ComponentScan(basePackages = "com.projektpool.example")
@EnableJpaRepositories("com.projektpool.example.dao.jpa") // To segregate MongoDB and JPA repositories. Otherwise not needed.
@EnableSwagger // auto generation of API docs
// public class Application extends SpringBootServletInitializer {
public class Application extends SpringBootServletInitializer implements CommandLineRunner{

    private static final Class<Application> applicationClass = Application.class;
    private static final Logger log = LoggerFactory.getLogger(applicationClass);

  @Autowired
  ProjectService projectService;


// example-Data
  @Override
	public void run(String... args) {
    Project createdProject = new Project("project1","blabla");
    projectService.createProject(createdProject);
	}

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      return application.sources(applicationClass);
  }

  public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
  }

  //TODO replace run()
  // @Bean
	// CommandLineRunner init(AccountRepository accountRepository,
	// 		BookmarkRepository bookmarkRepository) {
	// 	return ?
  // }

}
