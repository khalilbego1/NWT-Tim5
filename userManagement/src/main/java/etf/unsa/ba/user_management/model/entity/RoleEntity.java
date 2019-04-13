package etf.unsa.ba.user_management.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Roles")
public class RoleEntity implements Serializable {
    public enum Type {
        ADMIN,
        PASSENGER,
        EMPLOYEE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @NotNull(message = "{role.type.notNull}")
    @Enumerated(EnumType.STRING)
    @Column(unique = true, length = 50)
    private Type type;
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserEntity> users = new HashSet<>();

    @Override
    public String toString() {
        return String.format("%s", getType());
    }
}