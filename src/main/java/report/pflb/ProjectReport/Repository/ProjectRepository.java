package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import report.pflb.ProjectReport.Entity.Project;

import java.util.List;
@EnableJpaRepositories
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project save(Project project);
    boolean existsByName(String name);
    Project findByName(String Name);
    List<Project> findAll();
    Project findByProjectNumber(String projectNumber);

    Project findById(int id);
}
