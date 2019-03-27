package nwt.microservice.arrangements.Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "\"AdditionalActivity\"")
public class AdditionalActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer AdditionalActivityID;

    @ManyToMany(mappedBy = "additionalActivities")
    Set<Arrangement> arrangements;

    @ManyToOne
    @JoinColumn(name = "AdditionalActivityTypeId")
    private AdditionalActivityType additionalActivityTypeId;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
