package nwt.microservice.arrangements.Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Arrangement")
public class Arrangement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer ArrangementID;

    public Arrangement(){

    }

    @ManyToOne
    @JoinColumn(name = "\"ArrangementTypeID\"")
    private ArrangementType ArrangementTypeID;

//    @ManyToOne
//    @JoinColumn(name = "DestinationID")
//    private AdditionalActivityType DestinationID;

//    @ManyToOne
//    @JoinColumn(name = "TransportationID")
//    private AdditionalActivityType TransportationID;

    @ManyToMany
    @JoinTable(
            name = "ArrangementAdditionalActivity",
            joinColumns = @JoinColumn(name = "ArrangementID"),
            inverseJoinColumns = @JoinColumn(name = "AdditionalActivityID"))
    Set<AdditionalActivity> additionalActivities;
}
