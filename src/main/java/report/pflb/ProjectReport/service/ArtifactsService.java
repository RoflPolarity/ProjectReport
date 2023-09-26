package report.pflb.ProjectReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.pflb.ProjectReport.Entity.Artifact;
import report.pflb.ProjectReport.Repository.ArtifactsRepository;

import java.util.List;

@Service
public class ArtifactsService {

    private final ArtifactsRepository artifactsRepository;
    @Autowired
    public ArtifactsService(ArtifactsRepository artifactsRepository){this.artifactsRepository = artifactsRepository;}

    public Artifact save(Artifact artifact){return artifactsRepository.save(artifact);}
    public List<Artifact> findAllByProject_id(long id){return artifactsRepository.findByReport_id(id);}

}
