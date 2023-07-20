package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import report.pflb.ProjectReport.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
