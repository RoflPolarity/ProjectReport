package report.pflb.ProjectReport.Entity.LDAPUser;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(objectClasses = {"person"})
public class UserLdap {
    @Id
    private Name dn;

    @Attribute(name = "givenName")
    private String name;

    @Attribute(name = "sn")
    private String surname;

    @Attribute(name = "mail")
    private String mail;

    @Attribute(name = "name")
    private String login;

    @Override
    public String toString() {
        return "UserLdap{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mail='" + mail + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    public Name getDn() {
        return dn;
    }

    public void setDn(Name dn) {
        this.dn = dn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
