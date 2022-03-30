package Samgat.TManager.repositories;

import Samgat.TManager.entities.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<Projects,Long> {
    public List<Projects> findAllByOrderByStartDateDesc();
    public List<Projects> findAllByOrderByStartDate();
    public List<Projects> findAllByOrderByPriority();
}