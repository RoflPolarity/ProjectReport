package report.pflb.ProjectReport.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@Entity
@Table(name = "artifacts")
@NoArgsConstructor
@Getter
@Setter
public class Artifact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String path;
    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    public Artifact (String path, Report report){
        this.path = path;
        this.report = report;
    }

    @Override
    public String toString() {
        return new File(path).getName();
    }
}
