package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import report.pflb.ProjectReport.Entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
