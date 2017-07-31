package com.projektpool.example.service;

import com.projektpool.example.domain.Project;
import com.projektpool.example.dao.jpa.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
@Service
public class ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public ProjectService() {
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project getProject(long id) {
        return projectRepository.findOne(id);
    }

    public void updateProject(Project project) {
        projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.delete(id);
    }

    //http://goo.gl/7fxvVf
    public Page<Project> getAllProjects(Integer page, Integer size) {
        // TODO: create stubs
        Project createdProject = new Project("TestProjekt für Adesso ProjectPool intern 2017","Beschreibung des Test-Projektes");
        projectRepository.save(createdProject);

        Page pageOfProjects = projectRepository.findAll(new PageRequest(page, size));
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("projektpool.ProjectService.getAll.largePayload");
        }
        return pageOfProjects;
    }
}
