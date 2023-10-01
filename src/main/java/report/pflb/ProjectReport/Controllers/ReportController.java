package report.pflb.ProjectReport.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import report.pflb.ProjectReport.Entity.Artifact;
import report.pflb.ProjectReport.Entity.Report;
import report.pflb.ProjectReport.service.ArtifactsService;
import report.pflb.ProjectReport.service.projectService;
import report.pflb.ProjectReport.service.reportService;
import report.pflb.ProjectReport.service.userService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasAnyAuthority('ADMIN','PM', 'TM')")
@CrossOrigin
@RequiredArgsConstructor
public class ReportController {

    private final reportService reportService;
    private final userService userService;
    private final projectService projectService;
    private final ArtifactsService artifactsService;
    @GetMapping("/getReports/{projectNumber}")
    public List<Report> getReportByProjectNumber(@PathVariable String projectNumber){
        List<Report> lst = reportService.findByProjectId(Math.toIntExact(projectService.findByProjectNumber(projectNumber).getId()));
        lst.sort((r1, r2) -> r2.getDateCreate().compareTo(r1.getDateCreate()));
        for (Report report : lst){
            List<Artifact> artifacts = artifactsService.findAllByProject_id(report.getId());
            if (!artifacts.isEmpty()){
                List<String> names = new ArrayList<>();
                for (Artifact artifact : artifacts){
                    names.add(new File(artifact.getPath()).getName());
                }
                report.setArtifactPath(names);
            }
        }
        return lst;
    }

    @PostMapping("/addReport")
    public boolean addReport(@RequestParam("files") MultipartFile[] files,
                             @RequestParam("project_id") String project_id,
                             @RequestParam("text") String text,
                             @RequestParam("team_motivation") String teamMotivation,
                             @RequestParam("success_of_project") String successOfProject,
                             @RequestParam("customer_satisfaction") String customerSatisfaction) {
        try{
            Report report =  reportService.save(new Report(
                    userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()),
                    projectService.findByProjectNumber(project_id),
                    LocalDate.now(),
                    text,
                    teamMotivation,
                    successOfProject,
                    customerSatisfaction
            ));

            if (files != null) {
                for (MultipartFile file : files) {
                    LocalDate currentDate = LocalDate.now();
                    File projectFolder = new File("/home/a.yanpolsky/"+projectService.findByProjectNumber(project_id).getName());
                    if (!projectFolder.exists()) {
                        projectFolder.mkdir();
                    }
                    String currentDateFolderName = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String currentDateFolderPath = projectFolder.getPath() + "/" + currentDateFolderName;
                    File currentDateFolder = new File(currentDateFolderPath);
                    if (!currentDateFolder.exists()) {
                        currentDateFolder.mkdir();
                    }
                    String filePath = currentDateFolderPath + "/" + file.getOriginalFilename();
                    File destFile = new File(filePath);
                    if (!destFile.exists()) {
                        destFile.createNewFile();
                    }
                    try {
                        file.transferTo(destFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (destFile.exists()) {
                        artifactsService.save(new Artifact(destFile.getPath(), report));
                    }
                }
            }
            if (report != null) return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    static class ReportRequest{
        private String project_id, text, team_motivation, success_of_project, customer_satisfaction;

        public String getProject_id() {
            return project_id;
        }
        public void setProject_id(String project_id) {
            this.project_id = project_id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTeam_motivation() {
            return team_motivation;
        }

        public void setTeam_motivation(String team_motivation) {
            this.team_motivation = team_motivation;
        }


        public String getSuccess_of_project() {
            return success_of_project;
        }

        public void setSuccess_of_project(String success_of_project) {
            this.success_of_project = success_of_project;
        }

        public String getCustomer_satisfaction() {
            return customer_satisfaction;
        }

        public void setCustomer_satisfaction(String customer_satisfaction) {
            this.customer_satisfaction = customer_satisfaction;
        }
    }
}
