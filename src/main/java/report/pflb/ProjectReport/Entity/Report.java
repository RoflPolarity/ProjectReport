package report.pflb.ProjectReport.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "report")
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @Column(name = "date_create")
    private LocalDate dateCreate;
    @Column(name = "text")
    private String text;
    @Column(name = "team_mood", length = 10)
    private String teamMood;
    @Column(name = "project_activity", length = 10)
    private String projectActivity;
    @Column(name = "customer_satisfaction", length = 10)
    private String customerSatisfaction;
    @Transient
    private List<String> artifactPath;
    public Report(){}

    public Report(User user,
                  Project project,
                  LocalDate dateCreate,
                  String text,
                  String teamMotivation,
                  String successOfTheProject,
                  String customerSatisfaction
    ) {
        this.user = user;
        this.project = project;
        this.dateCreate = dateCreate;
        this.text = text;
        this.teamMood = teamMotivation;
        this.projectActivity = successOfTheProject;
        this.customerSatisfaction = customerSatisfaction;
    }

    public void setArtifactPath(List<String> artifactPath) {
        this.artifactPath = artifactPath;
    }

    @Override
    public String toString() {
        return "user: " + getUser() +
                "\n project: " + getProject() +
                "\n dateCreate: + " + dateCreate +
                "\n text: + " + text +
                "\n teamMood: " + teamMood +
                "\n projectActivity: " + projectActivity +
                "\n customerSatisfaction: " + customerSatisfaction +
                "\n artifacts: " + artifactPath;
    }
}
