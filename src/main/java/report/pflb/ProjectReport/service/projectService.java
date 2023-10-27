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
    public Project findByProjectNumber(String projectNumber){return projectRepository.findByProjectNumber(projectNumber);}
    public Project updateProjectByProjectNumber(String projectNumber , Project updatedProject){
        Project existingProject = projectRepository.findByProjectNumber(projectNumber);
        if (existingProject != null) {
            System.out.println(updatedProject.getName());
            existingProject.setName(updatedProject.getName());
            existingProject.setProjectNumber(updatedProject.getProjectNumber());
            existingProject.setDirection(updatedProject.getDirection());
            existingProject.setPm(updatedProject.getPm());
            existingProject.setTm(updatedProject.getTm());
            existingProject.setDateStart(updatedProject.getDateStart());
            existingProject.setDateEnd(updatedProject.getDateEnd());
            projectRepository.save(existingProject);
            return existingProject;
        }
        return null;
    }
}
