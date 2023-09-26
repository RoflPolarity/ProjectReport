package report.pflb.ProjectReport.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import report.pflb.ProjectReport.service.LdapService;
import report.pflb.ProjectReport.service.userService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class userControl {

    private final userService userService;
    private final LdapService ldapService;

    @Autowired
    @Lazy
    public userControl(userService userService, LdapService ldapService){
        this.userService = userService;
        this.ldapService = ldapService;
    }
/*
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/addProject")
    public Project addProject(@RequestBody Project project){
    }
*/
    @CrossOrigin
    @GetMapping("/api/GetPmList")
    public List<String> getAllUsersFromLDAP(){
        List<String> userFromLdap = new ArrayList<>(ldapService.getAllUsersFromLDAP().stream().filter(x -> x.contains(".")).map(user -> user + "@pflb.ru").toList());
        List<String> userList = userService.getAllFromDatabase().stream().map(user -> user.getLogin() + "@pflb.ru").toList();
        userFromLdap.addAll(0, userList);
        return new ArrayList<>(new HashSet<>(userFromLdap));
    }
}
