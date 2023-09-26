package report.pflb.ProjectReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.pflb.ProjectReport.Entity.Role;
import report.pflb.ProjectReport.Repository.RoleRepository;

@Service
public class roleService {
    private final RoleRepository roleRepository;
    @Autowired
    public roleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    public Role find(String name){return roleRepository.findByName(name);}
}
