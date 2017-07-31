package com.projektpool.example.api.rest;

import com.projektpool.example.domain.Project;
import com.projektpool.example.exception.DataFormatException;
import com.projektpool.example.service.ProjectService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/projects")
@Api(value = "project", description = "Project API")
public class ProjectController extends AbstractRestHandler {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a project resource.", notes = "Returns the URL of the new resource in the Location header.")
    public void createProject(@RequestBody Project project,
                                 HttpServletRequest request, HttpServletResponse response) {
        Project createdProject = this.projectService.createProject(project);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdProject.getId()).toString());
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all projects.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
    Page<Project> getAllProject(@ApiParam(value = "The page number (zero-based)", required = true)
                                      @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @ApiParam(value = "Tha page size", required = true)
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.projectService.getAllProjects(page, size);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single project.", notes = "You have to provide a valid project ID.")
    public
    @ResponseBody
    Project getProject(@ApiParam(value = "The ID of the project.", required = true)
                             @PathVariable("id") Long id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        Project project = this.projectService.getProject(id);
        checkResourceFound(project);
        //todo: http://goo.gl/6iNAkz
        return project;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a project resource.", notes = "You have to provide a valid project ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateProject(@ApiParam(value = "The ID of the existing project resource.", required = true)
                                 @PathVariable("id") Long id, @RequestBody Project project,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.projectService.getProject(id));
        if (id != project.getId()) throw new DataFormatException("ID doesn't match!");
        this.projectService.updateProject(project);
    }

    //todo: @ApiImplicitParams, @ApiResponses
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a project resource.", notes = "You have to provide a valid project ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteProject(@ApiParam(value = "The ID of the existing project resource.", required = true)
                                 @PathVariable("id") Long id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.projectService.getProject(id));
        this.projectService.deleteProject(id);
    }
}
