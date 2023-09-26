package report.pflb.ProjectReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.pflb.ProjectReport.Entity.Report;
import report.pflb.ProjectReport.Repository.ReportRepository;

import java.util.List;

@Service
public class reportService {

    private final ReportRepository reportRepository;

    @Autowired
    public reportService(ReportRepository reportRepository){
        this.reportRepository = reportRepository;
    }

    public Report save (Report report){return reportRepository.save(report);}
    public List<Report> findByProjectId(int projectId){return reportRepository.findByProject_id(projectId);}
}
