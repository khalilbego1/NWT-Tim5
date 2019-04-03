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
    public ArrangementType ArrangementTypeID;

//    @ManyToOne
//    @JoinColumn(name = "DestinationID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer DestinationID;

//    @ManyToOne
//    @JoinColumn(name = "TransportationID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer TransportationTypeID;

    @ManyToMany
    @JoinTable(
            name = "ArrangementAdditionalActivity",
            joinColumns = @JoinColumn(name = "ArrangementID"),
            inverseJoinColumns = @JoinColumn(name = "AdditionalActivityID"))
    public Set<AdditionalActivity> additionalActivities;
}
