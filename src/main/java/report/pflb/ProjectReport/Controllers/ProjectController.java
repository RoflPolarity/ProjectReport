package report.pflb.ProjectReport.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import report.pflb.ProjectReport.Entity.Project;
import report.pflb.ProjectReport.Entity.User;
import report.pflb.ProjectReport.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("hasAnyAuthority('ADMIN','PM', 'TM')")
@CrossOrigin
@RequiredArgsConstructor
public class ProjectController {

    private final projectService projectService;
    private final ObjectMapper objectMapper;
    private final userService userService;
    private final roleService roleService;
    private final directionService directionService;
    private final LdapService ldapService;

    @GetMapping("/api/getProject/{projectNumber}")
    public Project getProjectByNumber(@PathVariable String projectNumber){
        Project project = projectService.findByProjectNumber(projectNumber);
        List<User> tms = new ArrayList<>();
        List<User> pms = new ArrayList<>();
        for (int i = 0; i < project.getTm().size(); i++) tms.add(userService.findById(project.getTm().get(i)));
        for (int i = 0; i < project.getPm().size(); i++) pms.add(userService.findById(project.getPm().get(i)));
        project.setTms(tms);
        project.setPms(pms);
        return project;
    }

    @GetMapping("/api/GetAllProjects")
    public List<Project> getAllProjects(){
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Project> projectsToReturn = new ArrayList<>();
        List<Project> projects = projectService.findAll();
        for (Project project : projects){
            List<User> TMs = new ArrayList<>(), PMs = new ArrayList<>();
            for (Long TM : project.getTm()) TMs.add(userService.findById(TM));
            for (Long PM : project.getPm()) PMs.add(userService.findById(PM));
            project.setTms(TMs);
            project.setPms(PMs);
        }
        switch (user.getRole().getName()) {
            case "ADMIN" -> {return projects;}
            case "TM" -> {
                for (Project project : projects){
                    for (User TM : project.getTms()){
                        if (TM.getLogin().equals(user.getLogin())){
                            projectsToReturn.add(project);
                            break;
                        }
                    }
                }
                return projectsToReturn;
            }
            case "PM" -> {
                for (Project project : projects){
                    for (User PM : project.getPms()){
                        if (PM.getLogin().equals(user.getLogin())){
                            projectsToReturn.add(project);
                            break;
                        }
                    }
                }
                return projectsToReturn;
            }
            default -> {
                return null;
            }
        }
    }

    @PostMapping("api/EditProject")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public boolean editProject(@RequestBody String projectJSON){
        try {
            ProjectRequest request = objectMapper.readValue(projectJSON, ProjectRequest.class);
            Project project;
            List<Long> PMsLst = new ArrayList<>(), TMsLst = new ArrayList<>();
            List<String> pmsString = request.getPms(), tmsString = request.getTm();
            for (String pmStr : pmsString) PMsLst.add(userService.saveUser(User.migrateFromLdap(ldapService.findUserLdap_Mail(pmStr), roleService.find("PM"))).getId());
            for (String tmStr : tmsString) TMsLst.add(userService.saveUser(User.migrateFromLdap(ldapService.findUserLdap_Mail(tmStr), roleService.find("TM"))).getId());
            if (request.getEndDate()==null){
                project = new Project(
                        LocalDate.parse(request.getStartDate()),
                        null,
                        request.getProjectName(),
                        request.getStatus(),
                        PMsLst,
                        TMsLst,
                        request.getProjectNumber(),
                        directionService.find(request.direction)
                );
            }else {
                project = new Project(
                        LocalDate.parse(request.getStartDate()),
                        LocalDate.parse(request.getEndDate()),
                        request.getProjectName(),
                        request.getStatus(),
                        PMsLst,
                        TMsLst,
                        request.getProjectNumber(),
                        directionService.find(request.direction)
                );
            }
            return projectService.updateProjectByProjectNumber(request.getProjectNumber(), project) != null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @PostMapping("/api/AddProject")
    @PreAuthorize("hasAnyAuthority('ADMIN','PM')")
    public boolean addProject(@RequestBody String projectJSON){
        try {
            ProjectRequest request = objectMapper.readValue(projectJSON, ProjectRequest.class);
            List<Long> PMsLst = new ArrayList<>(), TMsLst = new ArrayList<>();
            List<String> pmsString = request.getPms(), tmsString = request.getTm();
            for (String pmStr : pmsString) PMsLst.add(userService.saveUser(User.migrateFromLdap(ldapService.findUserLdap_Mail(pmStr), roleService.find("PM"))).getId());
            for (String tmStr : tmsString) TMsLst.add(userService.saveUser(User.migrateFromLdap(ldapService.findUserLdap_Mail(tmStr), roleService.find("TM"))).getId());
            if (request.getEndDate()==null){
                projectService.save(new Project(
                        LocalDate.parse(request.getStartDate()),
                        null,
                        request.getProjectName(),
                        request.getStatus(),
                        PMsLst,
                        TMsLst,
                        request.getProjectNumber(),
                        directionService.find(request.direction)
                ));
            }else {
                projectService.save(new Project(
                        LocalDate.parse(request.getStartDate()),
                        LocalDate.parse(request.getEndDate()),
                        request.getProjectName(),
                        request.getStatus(),
                        PMsLst,
                        TMsLst,
                        request.getProjectNumber(),
                        directionService.find(request.direction)
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    static class ProjectRequest {
        private String projectName;
        private String projectNumber;
        private String startDate;
        private String endDate;
        private String status;
        private List<String> pmS;
        private List<String> tmS;
        private String direction;

        public ProjectRequest() {}

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectNumber() {
            return projectNumber;
        }

        public void setProjectNumber(String projectNumber) {
            this.projectNumber = projectNumber;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getPms() {
            return pmS;
        }

        public void setPm(List<String> pm) {
            this.pmS = pm;
        }

        public List<String> getTm() {
            return tmS;
        }

        public void setTm(List<String> tm) {
            this.tmS = tm;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }
    }
}
