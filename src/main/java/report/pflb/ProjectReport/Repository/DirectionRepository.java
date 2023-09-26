package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import report.pflb.ProjectReport.Entity.Direction;
@EnableJpaRepositories
@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {
    Direction findByName(String name);

}
