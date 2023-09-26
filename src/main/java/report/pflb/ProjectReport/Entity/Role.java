package report.pflb.ProjectReport.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role")
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    public Role(){}
    public Role (String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
