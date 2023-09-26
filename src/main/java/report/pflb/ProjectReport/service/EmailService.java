package report.pflb.ProjectReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import report.pflb.ProjectReport.Entity.Project;
import report.pflb.ProjectReport.Entity.Report;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private projectService projectService;
    @Autowired
    private reportService reportService;
    public void sendEmail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("report@pflb.ru");
        javaMailSender.send(message);
    }

    @Scheduled(cron = "0 18 * * *")
    public void checkReportAndNotify(){
        List<Project> projects = projectService.findAll();
        for(Project project : projects){
            System.out.println("Отработал");
            List<Report> reports = reportService.findByProjectId(Math.toIntExact(project.getId())).
                    stream().sorted(Comparator.comparing(Report::getDateCreate).reversed())
                    .toList();
            if (ChronoUnit.DAYS.between(LocalDate.now(),reports.get(0).getDateCreate()) >= 7){
                System.out.println(project.getTm().getLogin()+"@pflb.ru");
                //sendEmail(project.getTm().getLogin()+"@pflb.ru", "Заполнение отчета", "Добрый день!\nВам необходимо заполнить отчет");
            }
        }
    }

}
