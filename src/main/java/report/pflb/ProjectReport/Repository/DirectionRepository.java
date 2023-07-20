package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import report.pflb.ProjectReport.Entity.Direction;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
