package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import report.pflb.ProjectReport.Entity.User;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
     User save(User u);
     boolean existsByLogin(String login);
     User findByLogin(String login);
     List<User> findAll();
     User findById(long id);
}


