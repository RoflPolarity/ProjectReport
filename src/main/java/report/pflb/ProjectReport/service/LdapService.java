package report.pflb.ProjectReport.service;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;
import report.pflb.ProjectReport.Entity.LDAPUser.UserLdap;

import java.util.List;

@Service
public class LdapService {
    private final LdapTemplate ldapTemplate;

    public LdapService(LdapTemplate ldapTemplate){
        this.ldapTemplate = ldapTemplate;
    }

    public boolean auth(String login, String password){
        try {
            LdapQuery query = LdapQueryBuilder.query()
                    .base("OU=AT,OU=PFLB-Users&Groups,DC=pflb,DC=local")
                    .where("sAMAccountName").is(login);
            ldapTemplate.authenticate(query,password);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public List<String> getAllUsersFromLDAP() {
        LdapQuery query = LdapQueryBuilder.query()
                .base("OU=AT,OU=PFLB-Users&Groups,DC=pflb,DC=local")
                .where("objectClass").is("user");
        return  ldapTemplate.search(query, (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
    }

    public UserLdap findUserLdap_Mail(String mail){
        String login = mail.split("@")[0];
        LdapQuery query = LdapQueryBuilder.query()
                .base("OU=AT,OU=PFLB-Users&Groups,DC=pflb,DC=local")
                .where("name").is(login);
        return ldapTemplate.find(query, UserLdap.class).get(0);
    }

    public UserLdap findUserFromLdap(String login){
        LdapQuery query = LdapQueryBuilder.query()
                .base("OU=AT,OU=PFLB-Users&Groups,DC=pflb,DC=local")
                .where("sAMAccountName").is(login);
        var users = ldapTemplate.find(query, UserLdap.class);
        if (users.isEmpty()){
            return null;
        }else return users.get(0);
    }
}
