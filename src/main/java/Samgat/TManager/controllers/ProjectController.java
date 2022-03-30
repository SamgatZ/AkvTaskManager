package Samgat.TManager.controllers;

import Samgat.TManager.entities.ProjectStatus;
import Samgat.TManager.entities.Projects;
import Samgat.TManager.services.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;


    //list of projects
    @GetMapping()
    @Operation(summary = "get all projects")
    public ResponseEntity<?> getProjects() {
        List<Projects> projects = projectService.getALlProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping(value = "/{project_id}")
    @Operation(summary = "Get project by id")
    public ResponseEntity<?> getProject(@PathVariable(name = "project_id") long id) {
        Projects project = projectService.getProject(id);
        if (project!=null){
            return new ResponseEntity<>(project, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/addproject")
    @Operation(summary = "Add project")
    public ResponseEntity<?> addProject(@RequestParam(name = "projectname") String projectName,
                                        @RequestParam(name = "projectstatus", defaultValue = "NotStarted") ProjectStatus projectStatus ,
                                        @RequestParam(name = "priority", defaultValue = "0") long priority,
                                        @RequestParam(name = "completiondate", defaultValue = "2022-03-30") @DateTimeFormat(pattern = "yyyy-MM-dd") String completionDate) {
        Projects project = new Projects();
        project.setProjectName(projectName);
        project.setStatus(projectStatus);
        project.setStartDate(LocalDate.now());
        project.setCompletionDate(LocalDate.parse(completionDate));
        projectService.addProject(project);
        return new ResponseEntity<>(project,HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Delete project by id")
    public ResponseEntity<?> deleteProject(@PathVariable(name = "id") long pId) {
        projectService.deleteProject(pId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //update data
    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Upddate project by id",description = "Date time format yyyy-MM-dd for instance '2022-03-30'")
    public ResponseEntity<?> updateProject(@RequestParam(name = "projectname") String projectName,
                                           @RequestParam(name = "projectStatus",defaultValue = "NotStarted") ProjectStatus projectStatus,
                                           @RequestParam(name = "localdate", defaultValue = "2022-03-30") @DateTimeFormat(pattern = "yyyy-MM-dd") String completionDate,
                                           @RequestParam(name = "priority", defaultValue = "0") int priority,
                                           @PathVariable(name = "id") long id){
        Projects checkProject = projectService.getProject(id);
        if (checkProject!=null) {
            checkProject.setProjectName(projectName);
            checkProject.setStatus(projectStatus);
            checkProject.setStartDate(LocalDate.now());
            checkProject.setCompletionDate(LocalDate.parse(completionDate));
            checkProject.setPriority(priority);
            projectService.updateProject(checkProject);
            return new ResponseEntity<>(checkProject,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //list of completed projects
    @GetMapping(value = "/completed")
    @Operation(summary = "List of completed projects")
    public ResponseEntity<?> CompletedProjects() {
        List<Projects> projects = projectService.findCompletedProjects();
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }

    //list of projects with active status
    @GetMapping(value = "/active")
    @Operation(summary = "List of active projects")
    public ResponseEntity<?> activeProjects() {
        List<Projects> projects = projectService.findActiveProjects();
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }

    //list of projects with notstarted status
    @GetMapping(value = "/notstarted")
    @Operation(summary = "List of not started projects")
    public ResponseEntity<?> notStartedProjects() {
        List<Projects> projects = projectService.findNotStartedProjects();
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }

    //decreasing startproject data list
    @GetMapping(value = "/startdatedecr")
    @Operation(summary = "List of projects sorted by start date in decreasing order")
    public ResponseEntity<?> startDateDecrOrder() {
        List<Projects> projects = projectService.findByStartDateDecreasingOrder();
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }

    //ascending startproject data list
    @GetMapping(value = "/startdateasc")
    @Operation(summary = "List of projects sorted by start date in ascending order")
    public ResponseEntity<?> startDateAscOrder() {
        List<Projects> projects = projectService.findByStartDateAscendingOrder();
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }

    //return sorted by priority list
    @GetMapping(value = "/bypriority")
    @Operation(summary = "List of projects sorted by priority in ascending order")
    public ResponseEntity<?> projectsByPriority(){
        List<Projects> projects = projectService.findByPriority();
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }




}
