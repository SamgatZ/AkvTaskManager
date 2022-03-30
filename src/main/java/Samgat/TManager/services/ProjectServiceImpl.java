package Samgat.TManager.services;

import Samgat.TManager.entities.ProjectStatus;
import Samgat.TManager.entities.Projects;
import Samgat.TManager.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    ProjectRepository projectRepository;
    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }


    @Override
    public List<Projects> getALlProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void addProject(Projects project){

        Projects save = projectRepository.save(project);
    }

    @Override
    public void deleteProject(long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Projects getProject(long id){
        Optional<Projects> opt = projectRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public void updateProject(Projects project) {
        projectRepository.save(project);
    }


    @Override
    public List<Projects> findCompletedProjects() {
        return getALlProjects().stream()
                .filter(project -> (project.getStatus() == ProjectStatus.Completed))
                .collect(Collectors.toList());
    }

    @Override
    public List<Projects> findActiveProjects() {
        return getALlProjects().stream()
                .filter(project -> (project.getStatus() == ProjectStatus.Active))
                .collect(Collectors.toList());
    }

    @Override
    public List<Projects> findNotStartedProjects() {
        return getALlProjects().stream()
                .filter(project -> (project.getStatus() == ProjectStatus.NotStarted))
                .collect(Collectors.toList());
    }

    @Override
    public List<Projects> findByStartDateDecreasingOrder() {
        return projectRepository.findAllByOrderByStartDate();
    }

    @Override
    public List<Projects> findByStartDateAscendingOrder() {
        return projectRepository.findAllByOrderByStartDate();
    }

    @Override
    public List<Projects> findByPriority() {
        return projectRepository.findAllByOrderByPriority();
    }


}
