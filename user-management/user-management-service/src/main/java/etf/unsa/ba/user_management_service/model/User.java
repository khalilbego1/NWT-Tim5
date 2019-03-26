package etf.unsa.ba.user_management_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Role role;
}
