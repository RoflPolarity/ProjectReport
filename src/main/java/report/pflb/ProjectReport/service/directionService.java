package report.pflb.ProjectReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.pflb.ProjectReport.Entity.Direction;
import report.pflb.ProjectReport.Repository.DirectionRepository;
@Service
public class directionService {

    private final DirectionRepository directionRepository;
    @Autowired
    public directionService(DirectionRepository directionRepository){
        this.directionRepository = directionRepository;
    }
    public Direction find(String name){return directionRepository.findByName(name);}
}
