package report.pflb.ProjectReport.Entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import report.pflb.ProjectReport.Entity.LDAPUser.UserLdap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User  implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String login;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private String token;
    
    public User(){}

    public User(String name, String surname, String login, Role role){
        this.login = login;
        this.name = name;
        this.role = role;
        this.surname = surname;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return "null";
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setToken(String token) {this.token = token;}
    public String getToken() {return token;}
    public void setId(Long id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setSurname(String surname) {
        this.surname = surname;
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
    public Role getRole() {
        return role;
    }
    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", token='" + token + '\'' +
                '}';
    }

    public static User migrateFromLdap(UserLdap userLdap, Role role){
        return new User(userLdap.getName(), userLdap.getSurname(), userLdap.getLogin(), role);
    }
}
