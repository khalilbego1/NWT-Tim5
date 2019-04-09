package nwt.microservice.arrangements.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "\"UserArrangement\"")
public class UserArrangement {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer UserArrangementId;
    private Integer UserId;
}
