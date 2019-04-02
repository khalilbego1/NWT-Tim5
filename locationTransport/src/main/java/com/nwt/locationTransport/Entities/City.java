package com.nwt.locationTransport.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CITIES")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column (name = "NAME",nullable = false)
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Destination> destinations = new HashSet<>();

    public City(String name, Region region) {
        this.name = name;
        this.region = region;
    }
}

