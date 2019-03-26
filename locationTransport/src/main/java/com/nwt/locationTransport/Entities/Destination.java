package com.nwt.locationTransport.Entities;
import javax.persistence.*;

@Entity
@Table(name = "DESTINATIONS")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private City city;


}
