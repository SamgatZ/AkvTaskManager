package Samgat.TManager.services;

import Samgat.TManager.entities.Projects;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ProjectService {
    List<Projects> getALlProjects();
    public void addProject(Projects project);
    public void deleteProject(long id);
    public Projects getProject(long id);
    public void updateProject(Projects project);
    public List<Projects> findCompletedProjects();
    public List<Projects> findActiveProjects();
    public List<Projects> findNotStartedProjects();
    public List<Projects> findByStartDateDecreasingOrder();
    public List<Projects> findByStartDateAscendingOrder();
    public List<Projects> findByPriority();

}
