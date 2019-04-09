package nwt.microservice.arrangements.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Arrangement")
public class Arrangement {
    @Id
    @Column(name="ArrangementID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long ArrangementID;

    public Arrangement(){

    }

//    public Arrangement(Integer arrangementTypeId, String description){
//        ArrangementType arrType = new ArrangementType(arrangementTypeId, description);
//        this.arrangementType = arrType;
//    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "\"ArrangementTypeID\"")
    public ArrangementType arrangementType;

//    @ManyToOne
//      @JoinColumn(name = "DestinationID")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer DestinationID;

//   @ManyToOne
//    @JoinColumn(name = "TransportationID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer TransportationTypeID;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "ArrangementAdditionalActivity",
            joinColumns = @JoinColumn(name = "ArrangementID"),
            inverseJoinColumns = @JoinColumn(name = "AdditionalActivityID"))
    public Set<AdditionalActivity> additionalActivities;
}
