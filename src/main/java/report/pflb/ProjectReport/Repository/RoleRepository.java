package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import report.pflb.ProjectReport.Entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
    
}
