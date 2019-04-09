package nwt.microservice.arrangements.Entities;

import nwt.microservice.arrangements.Validations.FutureDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "\"ArrangementDuration\"")
public class ArrangementDuration {
    @Id
    @NotNull
    Integer ArrangementDurationID;
    @FutureDate
    Date StartDate;
    @FutureDate
    Date ReturnDate;
}
