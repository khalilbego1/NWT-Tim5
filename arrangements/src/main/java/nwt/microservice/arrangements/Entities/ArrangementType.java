package nwt.microservice.arrangements.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "\"ArrangementType\"")
public class ArrangementType {
    @Id
    @Column(name="ArrangementTypeID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long ArrangementTypeID;
    String description;

    public ArrangementType(Long arrangementTypeId) {
        this.ArrangementTypeID = arrangementTypeId;
    }
    public ArrangementType(Long arrangementTypeId, String description) {
//        this.ArrangementTypeID = arrangementTypeId;
        this.description = description;
    }
}
