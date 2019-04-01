package etf.unsa.ba.user_management.model.entity;

import etf.unsa.ba.user_management.validator.Adult;
import etf.unsa.ba.user_management.validator.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class UserEntity {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @NotBlank(message = "{user.firstName.notBlank}")
    private String firstName;
    @NotBlank(message = "Last name can't be blank")
    private String lastName;
    @NotBlank(message = "Username name can't be blank")
    @Size(min = 8, max = 20, message = "Username has to be between 8 and 20 characters long")
    @UniqueUsername(message = "{user.username.uniqueUsername}")
    private String username;
    @NotBlank(message = "Password name can't be blank")
    //@Size(min = 8, max = 20, message = "Password has to be between 8 and 20 characters long")
    private String password;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "roleId")
    private RoleEntity role;
    @NotBlank(message = "Email can't be blank")
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    private String email;
    @NotNull(message = "{user.dateOfBirth.notNull")
    @Past(message = "{user.dateOfBirth.past}")
    @Adult(message = "{user.dateOfBirth.adult}")
    private LocalDate dateOfBirth;
}

//TODO: set messages in message.properties