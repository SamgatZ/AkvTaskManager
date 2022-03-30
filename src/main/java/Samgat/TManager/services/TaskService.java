package Samgat.TManager.services;

import Samgat.TManager.entities.Projects;
import Samgat.TManager.entities.Tasks;

import java.util.List;

public interface TaskService {
    List<Tasks> getTasks(long projectId);
    List<Tasks> getAllTasks();
    void addTaskToProject(Tasks task, long projectId);
    boolean checkTaskContainsInProjects(long taskId, long projectId);
    Tasks getTask(long taskId);
    void updateTask(Tasks task);
    void deleteTask(Tasks task, Projects project);
    public List<Tasks> ToDoTasks();
    public List<Tasks> inProgressTasks();
    public List<Tasks> doneTasks();
}

