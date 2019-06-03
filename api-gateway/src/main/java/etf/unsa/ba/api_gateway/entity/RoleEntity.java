package etf.unsa.ba.api_gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    public enum Type {
        ADMIN,
        PASSENGER,
        EMPLOYEE
    }

    private int Id;
    private Type type;
    private String description;
    private Set<UserEntity> users = new HashSet<>();
}