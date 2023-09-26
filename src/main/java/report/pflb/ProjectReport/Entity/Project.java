package report.pflb.ProjectReport.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateStart;
    private String name;
    private LocalDate dateEnd;

    @ManyToOne
    @JoinColumn(name = "tm_id")
    private User tm;

    private String status;

    @Column(name = "project_number")
    private String projectNumber;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;
    @ManyToOne
    @JoinColumn(name = "pm_id")
    private User pm;

    public Project(){}

    public Project(LocalDate dateStart, LocalDate dateEnd, String name, String status, User pm, User tm, String projectNumber, Direction direction) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.pm = pm;
        this.tm = tm;
        this.status = status;
        this.projectNumber = projectNumber;
        this.direction = direction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getTm() {
        return tm;
    }

    public void setTm(User tm) {
        this.tm = tm;
    }

    public User getPm() {
        return pm;
    }

    public void setPm(User pm) {
        this.pm = pm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


}
