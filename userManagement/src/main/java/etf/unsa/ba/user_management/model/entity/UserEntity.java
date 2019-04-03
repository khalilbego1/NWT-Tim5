package etf.unsa.ba.user_management.model.entity;

import etf.unsa.ba.user_management.validator.Adult;
import etf.unsa.ba.user_management.validator.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
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
    @NotBlank(message = "{user.lastName.notBlank}")
    private String lastName;
    @NotBlank(message = "{user.username.notBlank}")
    @Size(min = 6, max = 10, message = "{user.username.size}")
    @UniqueUsername
    @Column(unique = true, length = 50)
    private String username;
    @NotBlank(message = "{user.password.notBlank}")
    @Size(min = 8, message = "{user.password.size}")
    private String password;
    @Valid
    @NotNull(message = "{user.role.notNull}")
    @ManyToOne
    @JoinColumn(name = "roleId")
    private RoleEntity role;
    @NotBlank(message = "{user.email.notBlank}")
    @Pattern(regexp = "[a-zA-Z0-9]+[._%+-]?[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[A-Za-z]{2,4}", message = "{user.email.regex}")
    private String email;
    @NotNull(message = "{user.dateOfBirth.notNull}")
    @Past(message = "{user.dateOfBirth.past}")
    @Adult
    private LocalDate dateOfBirth;
}