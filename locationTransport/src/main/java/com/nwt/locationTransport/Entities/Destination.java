package com.nwt.locationTransport.Entities;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DESTINATIONS")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "{destination.name.notBlank}")
    @Size(min =2, max = 25, message = "{destination.name.size}")
    @Column(name ="NAME",nullable = false)
    @NotNull
    private String name;
    @Valid
    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    public Destination(String name, City city) {
        this.name = name;
        this.city = city;
    }
}
