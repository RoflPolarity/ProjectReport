package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import report.pflb.ProjectReport.Entity.Report;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Report save(Report report);
    List<Report> findByProject_id(int projectId);

}
