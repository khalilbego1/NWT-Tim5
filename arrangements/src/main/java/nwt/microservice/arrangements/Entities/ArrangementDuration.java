package nwt.microservice.arrangements.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "\"ArrangementDuration\"")
public class ArrangementDuration {
    @Id
    Integer ArrangementDurationID;
    Date StartDate;
    Date ReturnDate;
}
