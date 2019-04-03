package nwt.microservice.arrangements.Entities;

import javax.persistence.*;

@Entity
@Table(name = "\"UserArrangement\"")
public class UserArrangement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer UserArrangementId;
    private Integer UserId;
}
