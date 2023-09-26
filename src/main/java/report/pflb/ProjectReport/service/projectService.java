package report.pflb.ProjectReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.pflb.ProjectReport.Entity.Project;
import report.pflb.ProjectReport.Repository.ProjectRepository;

import java.util.List;

@Service
public class projectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public projectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }
    public Project save(Project project){return projectRepository.save(project);}
    public boolean existByName(String name){return projectRepository.existsByName(name);}
    public Project findByName(String name){return projectRepository.findByName(name);}
    public List<Project> findAll(){return projectRepository.findAll();}

    public Project findById(int id){return projectRepository.findById(id);}
    public List<Project> findByPM(long id){return projectRepository.findByTm_id(id);}
    public List<Project> findByTM(long id){return projectRepository.findByPm_id(id);}
    public Project findByProjectNumber(String projectNumber){return projectRepository.findByProjectNumber(projectNumber);}
}
