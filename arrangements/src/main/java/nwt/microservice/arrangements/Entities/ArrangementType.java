package nwt.microservice.arrangements.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"ArrangementType\"")
public class ArrangementType {
    @Id
    Integer ArrangementType;
    String description;

}
