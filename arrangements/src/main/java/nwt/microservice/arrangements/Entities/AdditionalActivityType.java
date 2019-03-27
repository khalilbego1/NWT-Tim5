package nwt.microservice.arrangements.Entities;

import javax.persistence.*;

@Entity
@Table(name = "\"AdditionalActivityType\"")
public class AdditionalActivityType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer AdditionalActivityTypeId;
    private String Description;

    public Integer getAdditionalActivityTypeId() {
        return AdditionalActivityTypeId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
