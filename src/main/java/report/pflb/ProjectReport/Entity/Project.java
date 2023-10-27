package report.pflb.ProjectReport.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateStart;
    private String name;
    private LocalDate dateEnd;
    @Column(name = "tm_ids")
    private List<Long> tmIds;
    @Column(name = "pm_ids")
    private List<Long> pmIds;
    private String status;
    @Transient
    private List<User> pms = new ArrayList<>();
    @Transient
    private List<User> tms = new ArrayList<>();
    @Column(name = "project_number")
    private String projectNumber;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;


    public Project(){}

    public Project(LocalDate dateStart, LocalDate dateEnd, String name, String status, List<Long> pm, List<Long> tm, String projectNumber, Direction direction) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.pmIds = pm;
        this.tmIds = tm;
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

    public List<Long> getTm() {
        return tmIds;
    }

    public void setTm(List<Long> tm) {
        this.tmIds = tm;
    }

    public List<Long> getPm() {
        return pmIds;
    }

    public void setPm(List<Long> pm) {
        this.pmIds = pm;
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


    public List<User> getPms() {
        return pms;
    }

    public void setPms(List<User> pms) {
        this.pms = pms;
    }

    public List<User> getTms() {
        return tms;
    }

    public void setTms(List<User> tms) {
        this.tms = tms;
    }
}
