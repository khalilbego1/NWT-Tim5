package com.nwt.locationTransport.Entities;
import javax.persistence.*;

@Entity
@Table(name = "DESTINATIONS")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name ="NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    public Destination(String name, City city) {
        this.name = name;
        this.city = city;
    }
}
