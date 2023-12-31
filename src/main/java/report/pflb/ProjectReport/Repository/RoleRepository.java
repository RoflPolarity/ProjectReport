package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import report.pflb.ProjectReport.Entity.Role;
@EnableJpaRepositories
@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    Role findByName(String name);
}
