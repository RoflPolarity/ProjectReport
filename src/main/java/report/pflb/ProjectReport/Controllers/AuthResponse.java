package report.pflb.ProjectReport.Controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import report.pflb.ProjectReport.Entity.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private User user;
    private boolean status;
}
