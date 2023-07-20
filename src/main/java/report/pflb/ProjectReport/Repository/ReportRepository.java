package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import report.pflb.ProjectReport.Entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
