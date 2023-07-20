package report.pflb.ProjectReport.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String postName;
    private String email;
    private String phone;
    private String login;
    
    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    
    public User(){}

    public User(Long id, String name, String surname, String postName, String email, String phone, String login, Direction direction, Role role){
        this.direction = direction;
        this.email = email;
        this.id = id;
        this.login = login;
        this.name = name;
        this.phone = phone;
        this.postName = postName;
        this.role = role;
        this.surname = surname;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setPostName(String postName) {
        this.postName = postName;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
   
    public Direction getDirection() {
        return direction;
    }
    public String getEmail() {
        return email;
    }
    public Long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getPostName() {
        return postName;
    }
    public Role getRole() {
        return role;
    }
    public String getSurname() {
        return surname;
    }
}
