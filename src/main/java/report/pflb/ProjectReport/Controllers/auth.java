package report.pflb.ProjectReport.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import report.pflb.ProjectReport.Config.JwtService;
import report.pflb.ProjectReport.Entity.LDAPUser.UserLdap;
import report.pflb.ProjectReport.Entity.User;
import report.pflb.ProjectReport.service.EmailService;
import report.pflb.ProjectReport.service.LdapService;
import report.pflb.ProjectReport.service.userService;

@RestController
public class auth {
    @Autowired
    private userService userService;
    private final LdapService ldapService;
    private final JwtService jwtService;
    private final EmailService emailService;
    public auth(JwtService jwtService, LdapService ldapService, EmailService emailService) {
        this.jwtService = jwtService;
        this.ldapService = ldapService;
        this.emailService = emailService;
    }
    @CrossOrigin
    @PostMapping("/api/login")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest request) {
        System.out.println("SRABOTAL auth");
        if (ldapService.auth(request.getLogin(),request.getPassword())){
            UserLdap userLdap = ldapService.findUserFromLdap(request.getLogin());
            if (userLdap != null) {
                User user = userService.findByLogin(request.getLogin());
                String token = jwtService.generateToken(user);
                user.setToken(token);
                userService.saveUser(user);
                return ResponseEntity.ok(AuthResponse.builder()
                        .user(user)
                        .status(true)
                        .build());
            }
        }else {
            return ResponseEntity.ok(AuthResponse.builder()
                            .user(null)
                            .status(false)
                            .build());
        }
        return null;
    }
}