package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import report.pflb.ProjectReport.Entity.Artifact;

import java.util.List;
@EnableJpaRepositories
@Repository
public interface ArtifactsRepository extends JpaRepository<Artifact,Long> {

    Artifact save(Artifact artifact);
    List<Artifact> findByReport_id(long id);
}
