package etf.unsa.ba.api_gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private int Id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private LocalDate dateOfBirth;
    private RoleEntity role;
}