package Samgat.TManager.controllers;

import Samgat.TManager.entities.Projects;
import Samgat.TManager.entities.Tasks;
import Samgat.TManager.services.ProjectService;
import Samgat.TManager.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/")
public class TaskController {

    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;

    @GetMapping()
    @Operation(summary = "Get all tasks")
    public ResponseEntity<?> allTasks(){
        List<Tasks> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping(value = "/{project_id}")
    @Operation(summary = "Get task by project id")
    public ResponseEntity<?> projectTasks(@PathVariable(name = "project_id") long id){
        Projects checkProject = projectService.getProject(id);
        if(checkProject!=null) {
            return new ResponseEntity<>(taskService.getTasks(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/{project_id}/createTask")
    @Operation(summary = "Create task")
    public ResponseEntity<?> createTask(@RequestBody Tasks task,
                                        @PathVariable(name = "project_id") long id) {
        Projects checkProject = projectService.getProject(id);
        if (checkProject!=null && task!=null) {
            taskService.addTaskToProject(task,id);
            return new ResponseEntity<>(checkProject.getTasks(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{project_id}/edit/{task_id}")
    @Operation(summary = "Edit task")
    public ResponseEntity<?> editTask(@PathVariable(name = "project_id") long pId,
                                      @PathVariable(name = "task_id") long tId,
                                      @RequestBody Tasks task){
        Projects checkProject = projectService.getProject(pId);
        if (checkProject!=null && task!=null) {
            if (taskService.checkTaskContainsInProjects(tId,pId)){
                task.setId(tId);
                taskService.updateTask(task);
                return new ResponseEntity<>(task,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{project_id}/delete/{task_id}")
    @Operation(summary = "Delete task from project")
    public ResponseEntity<?> deleteTask(@PathVariable(name = "project_id") long pId,
                                        @PathVariable(name = "task_id") long tId) {
        Projects checkProject = projectService.getProject(pId);
        Tasks task = taskService.getTask(tId);
        if (checkProject!=null && task!=null) {
            if (taskService.checkTaskContainsInProjects(tId,pId)){
                taskService.deleteTask(task,checkProject);
                return new ResponseEntity<>(taskService.getAllTasks(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Return list of tasks with status ToDo
    @GetMapping(value = "/todotasks")
    @Operation(summary = "List of tasks with status ToDo")
    public ResponseEntity<?> toDoListTasks() {
        return new ResponseEntity<>(taskService.ToDoTasks(),HttpStatus.OK);
    }

    //Return list od tasks with status InProgress
    @GetMapping(value = "/inprogresstasks")
    @Operation(summary = "List of tasks with status InProgress")
    public ResponseEntity<?> inProgressTasks() {
        return new ResponseEntity<>(taskService.inProgressTasks(),HttpStatus.OK);
    }

    //Return list od tasks with status Done
    @GetMapping(value = "/donetasks")
    @Operation(summary = "List of tasks in the project with status InProgress")
    public ResponseEntity<?> doneTasks() {
        return new ResponseEntity<>(taskService.doneTasks(),HttpStatus.OK);
    }


}
