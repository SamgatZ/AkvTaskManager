package Samgat.TManager.services;

import Samgat.TManager.entities.Projects;
import Samgat.TManager.entities.TaskStatus;
import Samgat.TManager.entities.Tasks;
import Samgat.TManager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectService projectService;


    @Override
    public List<Tasks> getTasks(long projectId) {
        Projects project = projectService.getProject(projectId);
        return project.getTasks();
    }

    @Override
    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void addTaskToProject(Tasks task, long projectId) {
        Projects project = projectService.getProject(projectId);
        taskRepository.save(task);
        List<Tasks> tasks = new ArrayList<>();
        tasks = project.getTasks();
        tasks.add(task);
        project.setTasks(tasks);
        projectService.addProject(project);

    }

    @Override
    public boolean checkTaskContainsInProjects(long taskId, long projectId) {
        Projects project = projectService.getProject(projectId);
        return project.getTasks().contains(getTask(taskId));

    }

    @Override
    public Tasks getTask(long taskId) {
        Optional<Tasks> opt = taskRepository.findById(taskId);
        return opt.orElse(null);
    }

    @Override
    public void updateTask(Tasks task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Tasks task, Projects project) {
        project.getTasks().remove(task);
        taskRepository.delete(task);
    }

    @Override
    public List<Tasks> ToDoTasks() {
        return getAllTasks().stream()
                .filter(task -> (task.getStatus() == TaskStatus.ToDo))
                .collect(Collectors.toList());
    }

    @Override
    public List<Tasks> inProgressTasks() {
        return getAllTasks().stream()
                .filter(task -> (task.getStatus() == TaskStatus.InProgress))
                .collect(Collectors.toList());
    }

    @Override
    public List<Tasks> doneTasks() {
        return getAllTasks().stream()
                .filter(task -> (task.getStatus() == TaskStatus.Done))
                .collect(Collectors.toList());
    }


}