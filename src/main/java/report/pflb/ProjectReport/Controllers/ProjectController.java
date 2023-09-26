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
        return projectService.findByProjectNumber(projectNumber);
    }

    @GetMapping("/api/GetAllProjects")
    public List<Project> getAllProjects(){
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        return switch (user.getRole().getName()) {
            case "ADMIN" -> projectService.findAll();
            case "TM" -> projectService.findByPM(user.getId());
            case "PM" -> projectService.findByTM(user.getId());
            default -> null;
        };
    }

    @PostMapping("/api/AddProject")
    @PreAuthorize("hasAnyAuthority('ADMIN','PM')")
    public boolean addProject(@RequestBody String projectJSON){
        try {
            ProjectRequest request = objectMapper.readValue(projectJSON, ProjectRequest.class);
            projectService.save(new Project(
                    LocalDate.parse(request.getStartDate()),
                    LocalDate.parse(request.getEndDate()),
                    request.getProjectName(),
                    request.getStatus(),
                    userService.saveUser(User.migrateFromLdap(ldapService.findUserLdap_Mail(request.getPm()),roleService.find("PM"))),
                    userService.saveUser(User.migrateFromLdap(ldapService.findUserLdap_Mail(request.getTm()),roleService.find("TM"))),
                    request.getProjectNumber(),
                    directionService.find(request.direction)
                    ));

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
        private String pm;
        private String tm;
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

        public String getPm() {
            return pm;
        }

        public void setPm(String pm) {
            this.pm = pm;
        }

        public String getTm() {
            return tm;
        }

        public void setTm(String tm) {
            this.tm = tm;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }
    }
}
